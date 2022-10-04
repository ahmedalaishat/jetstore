package com.alaishat.ahmed.mobostore.ui.screens.basket

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.ahmed.mobostore.data.payment.paymentCards
import com.alaishat.ahmed.mobostore.data.payment.visaCard
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.model.BasketProduct
import com.alaishat.ahmed.mobostore.model.PaymentMethod
import com.alaishat.ahmed.mobostore.utils.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Sep/30/2022.
 * Mobo Store Project.
 */

data class BasketUiState(
    val basketProducts: List<BasketProduct> = emptyList(),
    val paymentMethods: List<PaymentMethod> = paymentCards,
    val selectedPaymentCard: PaymentMethod = visaCard,
//    val isLoading: Boolean = true,
    val errorMessages: List<ErrorMessage> = emptyList(),
)

@HiltViewModel
class BasketViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productsRepository: ProductsRepository,
    /// add user repository to use his address
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(BasketUiState())
    val uiState: StateFlow<BasketUiState> = _uiState.asStateFlow()


    init {
        // Observe for basket changes in the repo layer
        viewModelScope.launch {
            productsRepository.observeBasket().collect { basketProducts ->
                _uiState.update { it.copy(basketProducts = basketProducts) }
            }
        }
        // Observe for payment method changes in the repo layer
        viewModelScope.launch {
            productsRepository.observeSelectedPaymentCard().collect { selectedCard ->
                _uiState.update { it.copy(selectedPaymentCard = selectedCard) }
            }
        }
    }

//    fun addToBasket(product: Product) {
//        productsRepository.
//    }

    fun increaseBasketProductCount(productId: Int) {
        productsRepository.increaseBasketProductCount(productId)
    }

    fun decreaseBasketProductCount(productId: Int) {
        productsRepository.decreaseBasketProductCount(productId)
    }

    fun selectPaymentCard(carNumber: String) {
        productsRepository.selectPaymentMethod(paymentCards.first { card -> carNumber == card.carNumber })
    }
}