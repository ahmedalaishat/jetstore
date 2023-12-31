package com.alaishat.ahmed.jetstore.data.products

import com.alaishat.ahmed.jetstore.data.Repository
import com.alaishat.ahmed.jetstore.data.Result
import com.alaishat.ahmed.jetstore.model.BasketProduct
import com.alaishat.ahmed.jetstore.model.HomeCategory
import com.alaishat.ahmed.jetstore.model.PaymentMethod
import com.alaishat.ahmed.jetstore.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * JetStore Project.
 */
interface ProductsRepository : Repository {

    /**
     * Get product by id.
     */
    suspend fun getProduct(productId: Int): Result<Product>

    /**
     * Get home products.
     */
    suspend fun getHomeProducts(): Result<List<HomeCategory>>

    /**
     * Get home products.
     */
    suspend fun searchProduct(searchInput: String): Result<List<Product>>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<Int>>

    /**
     * Toggle a productId to be a favorite or not.
     */
    suspend fun toggleFavorite(productId: Int)

    /**
     * Observe the basket
     */
    fun observeBasket(): Flow<List<BasketProduct>>

    /**
     * Observe the basket
     */
    fun observeSelectedPaymentCard(): Flow<PaymentMethod>

    /**
     * Add a productId to basket.
     */
    fun addToBasket(product: Product): Boolean

    fun increaseBasketProductCount(productId: Int)

    fun decreaseBasketProductCount(productId: Int)

    fun selectPaymentMethod(paymentMethod: PaymentMethod)

}