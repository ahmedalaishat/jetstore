package com.alaishat.ahmed.mobostore.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.Product
import com.alaishat.ahmed.mobostore.utils.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Sep/29/2022.
 * Mobo Store Project.
 */

data class HomeUiState(
    val homeCategories: List<HomeCategory> = emptyList(),
    val searchProducts: List<Product> = emptyList(),
//    val isSearchOpen: Boolean = false,
    val favorites: Set<Int> = emptySet(),
    val isLoading: Boolean = true,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

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
                        val errorMessages = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.network_error
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
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
                        val errorMessages = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.network_error
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }


    /**
     * Toggle favorite of a product
     */
    fun toggleFavourite(productId: Int) {
        viewModelScope.launch {
            productsRepository.toggleFavorite(productId)
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

    /**
     * Notify that an error was displayed on the screen
     */
    fun errorShown(errorId: Long) {
        _uiState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }
}