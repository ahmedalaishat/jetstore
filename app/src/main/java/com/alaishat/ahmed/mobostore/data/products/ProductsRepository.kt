package com.alaishat.ahmed.mobostore.data.products

import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
interface ProductsRepository {

    /**
     * Get product by id.
     */
    suspend fun getProduct(productId: Int): Result<Product>

    /**
     * Get home products.
     */
    suspend fun getHomeProducts(): Result<List<HomeCategory>>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<Int>>

    /**
     * Toggle a productId to be a favorite or not.
     */
    suspend fun toggleFavorite(productId: Int)

}