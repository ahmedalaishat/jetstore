package com.alaishat.ahmed.mobostore.data.products.impl

import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.data.products.homeCategories
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.Product
import com.alaishat.ahmed.mobostore.utils.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
class FakeProductsRepository : ProductsRepository {

    private val favorites = MutableStateFlow<Set<Int>>(setOf())

    override suspend fun getProduct(productId: Int): Result<Product> {
        return withContext(Dispatchers.IO) {
            val post = homeCategories.flatMap { it.products }.find { product -> product.id == productId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Unable to find product"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getHomeProducts(): Result<List<HomeCategory>> {
        return Result.Success(homeCategories)
    }

    override fun observeFavorites(): Flow<Set<Int>> {
        return favorites
    }

    override suspend fun toggleFavorite(productId: Int) {
        val set = favorites.value.toMutableSet()
        set.addOrRemove(productId)
        favorites.value = set
    }

}