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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.AppButton
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
fun SingleItemScreen(navController: NavController) {
    val context = LocalContext.current

    var isLoved by remember { mutableStateOf(false) }

    val colors =
        listOf(
            ItemColor("Sky Blue", 0xFF7485C1),
            ItemColor("Rose Gold", 0xFFC9A19C),
            ItemColor("Green", 0xFFA1C89B)
        )
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
            ItemImagesPager()
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
                        text = "2020 Apple iPad Air 10.9",
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
                        items(count = colors.size, itemContent = { position ->
                            ColorCard(
                                itemColor = colors[position],
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
                            text = "\$ 954",
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
fun ColorCard(itemColor: ItemColor, isSelected: Boolean, onSelect: () -> Unit) {
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
            Ball(color = Color(itemColor.color), size = 16.dp)
            HorizontalSpacer(width = 8.dp)
            Text(
                text = itemColor.desc,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemImagesPager() {
    HorizontalPager(
        modifier = Modifier.padding(top = 50.dp),
        count = 5, // AHMED_TODO call api to get item images
        contentPadding = PaddingValues(start = 70.dp, end = 110.dp)
    ) { item ->
        // Calculate the absolute offset for the current page from the
        // scroll position. We use the absolute value which allows us to mirror
        // any effects for both directions
        val pageOffset = calculateCurrentOffsetForPage(item).absoluteValue
        val img = if (item % 2 == 1) R.drawable.item_basket2 else R.drawable.item_basket
        Image(
            painterResource(id = img),
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
        SingleItemScreen(rememberNavController())
    }
}


data class ItemColor(val desc: String, val color: Long)