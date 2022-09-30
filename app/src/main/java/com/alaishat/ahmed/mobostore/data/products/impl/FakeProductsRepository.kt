package com.alaishat.ahmed.mobostore.data.products.impl

import com.alaishat.ahmed.mobostore.data.Result
import com.alaishat.ahmed.mobostore.data.products.ProductsRepository
import com.alaishat.ahmed.mobostore.data.products.homeCategories
import com.alaishat.ahmed.mobostore.model.HomeCategory
import com.alaishat.ahmed.mobostore.model.Product
import com.alaishat.ahmed.mobostore.utils.addOrRemove
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

    override suspend fun getProduct(productId: Int): Result<Product> {
        return withContext(ioDispatcher) {
            delay(800) // pretend we're on a slow network
//            val product: Product? = null
            val product = homeCategories.flatMap { it.products }.find { product -> product.id == productId }
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

}