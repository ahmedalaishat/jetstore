package com.alaishat.ahmed.mobostore.data.products.impl

import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.payment.visaCard
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.data.products.homeCategories
import com.alaishat.ahmed.mobostore.model.BasketProduct
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.PaymentMethod
import com.alaishat.ahmed.mobostore.model.Product
import com.alaishat.ahmed.mobostore.utils.addOrRemove
import com.skydoves.whatif.whatIfMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * Mobo Store Project.
 */
class FakeProductsRepository @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
) : ProductsRepository {

    private val favorites = MutableStateFlow<Set<Int>>(setOf())

    private val basketProducts = MutableStateFlow<List<BasketProduct>>(emptyList())

    private val selectedPaymentMethod = MutableStateFlow(visaCard)

    override suspend fun getProduct(productId: Int): Result<Product> {
        return withContext(ioDispatcher) {
            delay(800) // pretend we're on a slow network
            val product: Product? = null
//            val product = homeCategories.flatMap { it.products }.find { product -> product.id == productId }
            if (product == null) {
                Result.Error(IllegalArgumentException("Unable to find product"))
            } else {
                Result.Success(product)
            }
        }
    }

    override suspend fun getHomeProducts(): Result<List<HomeCategory>> {
        return withContext(ioDispatcher) {
            delay(800) // pretend we're on a slow network
//            Result.Error(IllegalArgumentException("Unable to find product"))
            Result.Success(homeCategories)
        }
    }

    override suspend fun searchProduct(searchInput: String): Result<List<Product>> {
        return Result.Success(homeCategories.flatMap { it.products }.filter { product ->
            product.title.contains(searchInput, true) || product.subtitle.contains(searchInput, true)
        })
    }

    override fun observeFavorites(): Flow<Set<Int>> {
        return favorites
    }

    override suspend fun toggleFavorite(productId: Int) {
        val set = favorites.value.toMutableSet()
        set.addOrRemove(productId)
        favorites.value = set
    }

    override fun observeBasket(): Flow<List<BasketProduct>> {
        return basketProducts
    }

    override fun observeSelectedPaymentCard(): Flow<PaymentMethod> {
        return selectedPaymentMethod
    }

    override fun addToBasket(product: Product): Boolean {
        val set = basketProducts.value.toMutableList()
        if (set.firstOrNull { it.id == product.id } != null) return false
        set.add(product.toBasketProduct())
        basketProducts.value = set
        return true
    }

    override fun increaseBasketProductCount(productId: Int) {
        val set = basketProducts.value.map { product ->
            product.whatIfMap(
                given = product.id == productId,
                whatIf = { it.copy(count = it.count + 1) },
                whatIfNot = { it })
        }
        basketProducts.value = set
    }

    override fun decreaseBasketProductCount(productId: Int) {
        val set = basketProducts.value.map { product ->
            product.whatIfMap(
                given = product.id == productId,
                whatIf = { it.copy(count = it.count - 1) },
                whatIfNot = { it })
        }
        basketProducts.value = set
    }

    override fun selectPaymentMethod(paymentMethod: PaymentMethod) {
        selectedPaymentMethod.value = paymentMethod
    }

}