package com.alaishat.ahmed.jetstore.model

import androidx.annotation.StringRes
import com.alaishat.ahmed.jetstore.R

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * JetStore Project.
 */
data class HomeCategory(
    val products: List<Product>,
    val category: ProductCategory,
)

enum class ProductCategory(
    @StringRes val tabName: Int
) {
    WEARABLES(R.string.wearables),
    LAPTOPS(R.string.laptops),
    PHONES(R.string.phones),
    Buds(R.string.buds)
}
