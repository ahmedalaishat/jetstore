package com.alaishat.ahmed.mobostore.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Sep/29/2022.
 * Mobo Store Project.
 */

data class HomeUiState(
    val homeCategories: List<HomeCategory> = emptyList(),
    val searchProducts: List<Product> = emptyList(),
    val favorites: Set<Int> = emptySet(),
    val isLoading: Boolean = true,
    val searchInput: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // See https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
    // For why channel > SharedFlow/StateFlow in this case
    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents = _oneShotEvents.receiveAsFlow()

    init {
        refreshProducts()

        // Observe for favorite changes in the repo layer
        viewModelScope.launch {
            productsRepository.observeFavorites().collect { favorites ->
                _uiState.update { it.copy(favorites = favorites) }
            }
        }
    }

    /**
     * Refresh products and update the UI state accordingly
     */
    fun refreshProducts() {
        // Ui state is refreshing
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = productsRepository.getHomeProducts()
            _uiState.update {
                when (result) {
                    is Result.Success -> {
                        val data = result.data
                        it.copy(homeCategories = data, isLoading = false)
                    }
                    is Result.Error -> {
                        viewModelScope.launch {
                            _oneShotEvents.send(OneShotEvent.NetworkError)
                        }
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }

    /**
     * search product and update the UI state accordingly
     */
    fun searchProduct() {
        // Ui state is refreshing
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = productsRepository.searchProduct(_uiState.value.searchInput)
            _uiState.update {
                when (result) {
                    is Result.Success -> {
                        val data = result.data
                        it.copy(searchProducts = data, isLoading = false)
                    }
                    is Result.Error -> {
                        _oneShotEvents.send(OneShotEvent.NetworkError)
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }


    /**
     * Notify that the user updated the search query
     */
    fun onSearchInputChanged(searchInput: String) {
        _uiState.update {
            it.copy(searchInput = searchInput)
        }
        searchProduct()
    }

    /**
     * Notify that the user interacted with the feed
     */
    fun searchOpened(isOpened: Boolean) {
        _uiState.update {
            it.copy(searchProducts = emptyList(), searchInput = "")
        }
    }

//    /**
//     * Notify that an error was displayed on the screen
//     */
//    fun errorShown(errorId: Long) {
//        _uiState.update { currentUiState ->
//            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
//            currentUiState.copy(errorMessages = errorMessages)
//        }
//    }

    sealed class OneShotEvent(val message: Int? = null) {
        object NetworkError : OneShotEvent(R.string.network_error)
    }
}