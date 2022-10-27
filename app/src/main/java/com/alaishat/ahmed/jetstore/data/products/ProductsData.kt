package com.alaishat.ahmed.jetstore.data.products

import com.alaishat.ahmed.jetstore.R
import com.alaishat.ahmed.jetstore.model.HomeCategory
import com.alaishat.ahmed.jetstore.model.Product
import com.alaishat.ahmed.jetstore.model.ProductCategory
import com.alaishat.ahmed.jetstore.model.ProductColor

/**
 * Created by Ahmed Al-Aishat on Sep/28/2022.
 * JetStore Project.
 */
val samsungWatchActive2 =
    Product(
        1, "Samsung Watch", "Active 2", price = 199, imageId = R.drawable.samsung_watch_active2,
        images = listOf(
            R.drawable.samsung_watch_active2_1,
            R.drawable.samsung_watch_active2_3,
            R.drawable.samsung_watch_active2_4,
            R.drawable.samsung_watch_active2_2,
            R.drawable.samsung_watch_active2_5,
            R.drawable.samsung_watch_active2_6,
        ),
        colors = listOf(
            ProductColor(R.string.color_name_silver, R.color.watch_silver),
            ProductColor(R.string.color_name_black, R.color.black),
            ProductColor(R.string.color_name_rose_gold, R.color.watch_gold),
        ),
    )

val appleWatchSeries8 = Product(
    2, "Apple Watch", "Series 8", price = 849, imageId = R.drawable.apple_watch_series8,
    images = listOf(
        R.drawable.apple_watch_series8_1,
        R.drawable.apple_watch_series8_2,
        R.drawable.apple_watch_series8_3,
    ),
    colors = listOf(
        ProductColor(R.string.color_name_graphite, R.color.buds_graphite),
        ProductColor(R.string.color_name_silver, R.color.watch_silver),
        ProductColor(R.string.color_name_rose_gold, R.color.watch_gold),
    ),
)


val samsungGalaxyS22Ultra =
    Product(
        3, "Samsung Galaxy", "S22 Ultra 5G", price = 999, imageId = R.drawable.samsung_galaxy_s22,
        images = listOf(
            R.drawable.samsung_galaxy_s22,
            R.drawable.samsung_galaxy_s22_2,
            R.drawable.samsung_galaxy_s22_3,
            R.drawable.samsung_galaxy_s22_4,
        ),
        colors = listOf(
            ProductColor(R.string.color_name_white, R.color.buds_white),
            ProductColor(R.string.color_name_black, R.color.watch_black),
            ProductColor(R.string.color_name_bink_gold, R.color.phone_pink_gold),
        ),
    )

val appleIpadAir220 =
    Product(
        4, "Apple iPad Air", "2020 10.9\"", price = 639, imageId = R.drawable.apple_ipad_air4_2020,
        images = listOf(
            R.drawable.apple_ipad_air4_2020_1,
            R.drawable.apple_ipad_air4_2020_2,
            R.drawable.apple_ipad_air4_2020_3,
            R.drawable.apple_ipad_air4_2020_4,
            R.drawable.apple_ipad_air4_2020_5,
        ),
        colors = listOf(
            ProductColor(R.string.color_name_rose_gold, R.color.phone_rose_gold),
            ProductColor(R.string.color_name_sky_blue, R.color.phone_sky_blue),
            ProductColor(R.string.color_name_green, R.color.phone_green),
        ),
    )

val samsungBuds =
    Product(
        5, "Samsung Buds", "Pro 2", price = 219, imageId = R.drawable.samsung_buds_pro2,
        images = listOf(
            R.drawable.samsung_buds_pro2_1,
            R.drawable.samsung_buds_pro2_2,
            R.drawable.samsung_buds_pro2_3,
        ),
        colors = listOf(
            ProductColor(R.string.color_name_white, R.color.buds_white),
            ProductColor(R.string.color_name_bora_purple, R.color.buds_bora_purple),
            ProductColor(R.string.color_name_graphite, R.color.buds_graphite),
        )
    )

val appleAirPods = Product(
    6, "APPLE AirPods", "Pro 3", price = 249, imageId = R.drawable.apple_air_puds2,
    images = listOf(
        R.drawable.apple_air_puds_1,
        R.drawable.apple_air_puds_2,
        R.drawable.apple_air_puds_3,
    ),
    colors = listOf(ProductColor(R.string.color_name_white, R.color.buds_white))
)


val homeCategories: List<HomeCategory> =
    listOf(
        HomeCategory(
            listOf(samsungWatchActive2, appleWatchSeries8),
            ProductCategory.WEARABLES,
        ),
        HomeCategory(
            emptyList(),
            ProductCategory.LAPTOPS,
        ),
        HomeCategory(
            listOf(samsungGalaxyS22Ultra, appleIpadAir220),
            ProductCategory.PHONES,
        ),
        HomeCategory(
            listOf(samsungBuds, appleAirPods),
            ProductCategory.Buds,
        ),
    )