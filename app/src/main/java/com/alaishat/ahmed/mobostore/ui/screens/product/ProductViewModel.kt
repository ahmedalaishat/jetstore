package com.alaishat.ahmed.mobostore.ui.screens.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
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

data class ProductUiState(
    val product: Product? = null,
    val favorites: Set<Int> = emptySet(),
    val selectedColor: Int = -1,
    val isLoading: Boolean = true,
    val errorMessages: List<ErrorMessage> = emptyList(),
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
    fun toggleFavourite(): Unit {
        viewModelScope.launch {
            productsRepository.toggleFavorite(productId)
        }
    }


    /**
     * Select product color
     */
    fun selectColor(position: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedColor = position) }
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