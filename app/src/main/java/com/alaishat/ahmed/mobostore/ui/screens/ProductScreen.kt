package com.alaishat.ahmed.mobostore.ui.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.data.products.*
import com.alaishat.ahmed.mobostore.model.ProductColor
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.mobostore.ui.components.headers.AppHeader
import com.alaishat.ahmed.mobostore.ui.components.shape.Ball
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.animatePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

/**
 * Created by Ahmed Al-Aishat on Aug/17/2022.
 * Mobo Store Project.
 * Copyright (c) 2022 Cloud Systems. All rights reserved.
 */
@Composable
fun ProductScreen(navController: NavController) {
    val context = LocalContext.current

    val product = samsungBuds

    var isLoved by remember { mutableStateOf(false) }

    var selectedColor by remember { mutableStateOf(1) }

    val toggleLove = {
        isLoved = !isLoved
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppHeader(
            "",
            onClickLeftIcon = { navController.popBackStack() },
            onClickRightIcon = { toggleLove() },
            rightIconId = if (isLoved) R.drawable.ic_love_selected else R.drawable.ic_love
        )
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            ItemImagesPager(product.images)
            VerticalSpacer(height = 10.dp)
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 20.dp, horizontal = 35.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = product.title + " " + product.subtitle,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                    )
                    VerticalSpacer(height = 10.dp)
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Colors",
                        style = MaterialTheme.typography.labelMedium,
                    )
                    VerticalSpacer(height = 10.dp)
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        items(count = product.colors.size, itemContent = { position ->
                            ColorCard(
                                itemColor = product.colors[position],
                                isSelected = position == selectedColor
                            ) { selectedColor = position }
                        })
                    }
                    VerticalSpacer(height = 40.dp)
                    Text(
                        text = "Get Apple TV+ free for a year",
                        style = MaterialTheme.typography.labelMedium,
                    )
                    VerticalSpacer(height = 5.dp)
                    Text(
                        text = "Available when you purchase any new iPhone, iPad, iPod Touch, Mac or Apple TV, £4.99/month after free trial.\"",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    VerticalSpacer(height = 5.dp)
                    Row(
                        Modifier.clickable { },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.absoluteOffset(y = (-3).dp),
                            text = "Full description",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium,
                        )
                        HorizontalSpacer(width = 8.dp)
                        Icon(
                            painter = painterResource(id = R.drawable.ic_vector),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                    VerticalSpacer(height = 30.dp)
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Total",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = AppTypefaceTokens.WeightRegular),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "\$ ${product.price}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End
                        )
                    }
                    VerticalSpacer(height = 50.dp)
                    PrimaryButton(
                        text = "Add to basket",
                        modifier = Modifier.align(CenterHorizontally),
                        onClick = {
                            Toast.makeText(context, "Item added to basket", Toast.LENGTH_LONG).show()
//                            navController.popBackStack()
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun ColorCard(itemColor: ProductColor, isSelected: Boolean, onSelect: () -> Unit) {
    val border = if (isSelected) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outlineVariant

    Card(
        border = BorderStroke(1.dp, border)
    ) {
        Row(
            Modifier
                .clickable { onSelect() }
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Ball(color = colorResource(id = itemColor.colorId), size = 16.dp)
            HorizontalSpacer(width = 8.dp)
            Text(
                text = stringResource(id = itemColor.colorName),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemImagesPager(images: List<Int>) {
    HorizontalPager(
        modifier = Modifier.padding(top = 50.dp),
        count = images.size,
        contentPadding = PaddingValues(start = 70.dp, end = 110.dp)
    ) { item ->
        // Calculate the absolute offset for the current page from the
        // scroll position. We use the absolute value which allows us to mirror
        // any effects for both directions
        val pageOffset = calculateCurrentOffsetForPage(item).absoluteValue
        Image(
            painterResource(id = images[item]),
            modifier = Modifier
                .animatePage(pageOffset)
                .size(width = 230.dp, height = 265.dp),
            contentDescription = ""
        )
    }
}


@Composable
fun SingleItemPreview() {
    MoboStoreTheme {
        ProductScreen(rememberNavController())
    }
}