package com.alaishat.ahmed.mobostore.ui.screens.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.data.products.homeCategories
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

data class ProductUiState(
    val product: Product? = null,
    val favorites: Set<Int> = emptySet(),
    val selectedColor: Int = -1,
    val isLoading: Boolean = true,
)

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    // See https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
    // For why channel > SharedFlow/StateFlow in this case
    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents = _oneShotEvents.receiveAsFlow()

    init {
        refreshProduct()

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
    fun refreshProduct() {
        // Ui state is refreshing
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = productsRepository.getProduct(productId)
            _uiState.update {
                when (result) {
                    is Result.Success -> {
                        val color = if (result.data.colors.size > 1) 1 else result.data.colors.size - 1
                        it.copy(product = result.data, isLoading = false, selectedColor = color)
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
     * Toggle favorite of a product
     */
    fun toggleFavourite() {
        viewModelScope.launch {
            productsRepository.toggleFavorite(productId)
        }
    }

    /**
     * Add product to basket
     */
    fun addToBasket(productId: Int) {
        val isAdded =
            productsRepository.addToBasket(homeCategories.flatMap { it.products }.first { it.id == productId })
        val event = if (isAdded) OneShotEvent.ItemAdded else OneShotEvent.ItemAlreadyExist
        viewModelScope.launch {
            _oneShotEvents.send(event)
        }
//        _uiState.update {
//            val errorMessages = it.addMessages + ErrorMessage(
//                id = UUID.randomUUID().mostSignificantBits,
//                messageId = if (isAdded) R.string.item_added_to_basket else R.string.item_already_added_to_basket
//            )
//            it.copy(addMessages = errorMessages)
//        }
    }


    /**
     * Select product color
     */
    fun selectColor(position: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedColor = position) }
        }
    }

//    /**
//     * Notify that an error was displayed on the screen
//     */
//    fun messageShown(errorId: Long) {
//        _uiState.update { currentUiState ->
//            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
//            val addMessages = currentUiState.addMessages.filterNot { it.id == errorId }
//            currentUiState.copy(errorMessages = errorMessages, addMessages = addMessages)
//        }
//    }

    sealed class OneShotEvent(val message: Int? = null) {
        object NetworkError : OneShotEvent(R.string.network_error)
        object ItemAdded : OneShotEvent(R.string.item_added_to_basket)
        object ItemAlreadyExist : OneShotEvent(R.string.item_already_added_to_basket)
    }
}