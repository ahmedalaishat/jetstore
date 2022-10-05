package com.alaishat.ahmed.mobostore.ui.screens.product

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alaishat.ahmed.mobostore.R
import com.alaishat.ahmed.mobostore.model.ProductColor
import com.alaishat.ahmed.mobostore.ui.components.FullScreenLoading
import com.alaishat.ahmed.mobostore.ui.components.HorizontalSpacer
import com.alaishat.ahmed.mobostore.ui.components.VerticalSpacer
import com.alaishat.ahmed.mobostore.ui.components.buttons.PrimaryButton
import com.alaishat.ahmed.mobostore.ui.components.headers.AppHeader
import com.alaishat.ahmed.mobostore.ui.components.shape.Ball
import com.alaishat.ahmed.mobostore.ui.navigation.Screen
import com.alaishat.ahmed.mobostore.ui.screens.NoConnectionScreen
import com.alaishat.ahmed.mobostore.ui.theme.AppTypefaceTokens
import com.alaishat.ahmed.mobostore.ui.theme.MoboStoreTheme
import com.alaishat.ahmed.mobostore.utils.animatePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlin.math.absoluteValue

/**
 * Created by Ahmed Al-Aishat on Aug/17/2022.
 * Mobo Store Project.
 */
@Composable
fun ProductScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    productViewModel: ProductViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val onGoToBasketState by rememberUpdatedState { navController.navigate(route = Screen.Basket.route) }
    val onRefreshState by rememberUpdatedState { productViewModel.refreshProduct() }

    LaunchedEffect(snackbarHostState) {
        productViewModel.oneShotEvents
            .onEach {
                val (actionLabel, action) =
                    when (it) {
                        ProductViewModel.OneShotEvent.NetworkError -> Pair(R.string.retry, onRefreshState)
                        ProductViewModel.OneShotEvent.ItemAdded,
                        ProductViewModel.OneShotEvent.ItemAlreadyExist -> Pair(R.string.basket, onGoToBasketState)
                    }
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = context.getString(it.message!!),
                    actionLabel = context.getString(actionLabel)
                )
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    action()
                }
            }.collect()
    }

    // UiState of the HomeScreen
    val uiState by productViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = CenterHorizontally,
    ) {

        if (uiState.isLoading)
            FullScreenLoading()
        else if (uiState.product != null)
            ProductInfoContent(
                navController,
                uiState,
                { productViewModel.toggleFavourite() },
                { productViewModel.selectColor(it) },
                { productViewModel.addToBasket(it) }
            )
        else {
//            if (uiState.errorMessages.isEmpty()) {
//                // if there are no products, and no error, let the user refresh manually
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    NoConnectionScreen { productViewModel.refreshProduct() }
                }
//            } else {
//                // there's currently an error showing, don't show any content
//                Box(Modifier.fillMaxSize()) { /* empty screen */ }
//            }
        }
    }
}

@Composable
private fun ProductInfoContent(
    navController: NavController,
    uiState: ProductUiState,
    toggleFavourite: () -> Unit,
    selectColor: (Int) -> Unit,
    addToBasket: (Int) -> Unit,
) {
    val context = LocalContext.current

    val isLoved = uiState.favorites.contains(uiState.product?.id)
    val product = uiState.product ?: return

    AppHeader(
        "",
        onClickLeftIcon = { navController.popBackStack() },
        onClickRightIcon = { toggleFavourite() },
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
                            isSelected = position == uiState.selectedColor
                        ) { selectColor(position) }
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
                val message = stringResource(R.string.item_added_to_basket)
                PrimaryButton(
                    text = stringResource(R.string.add_to_basket),
                    modifier = Modifier.align(CenterHorizontally),
                    onClick = {
                        addToBasket(product.id)
                    },
                )
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
@Preview
fun ProductPreview() {
    MoboStoreTheme {
        ProductScreen(rememberNavController(), SnackbarHostState())
    }
}