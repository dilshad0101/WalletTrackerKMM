package com.app.spendr.presentation.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.app.spendr.R


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun BalanceCard(
    balance:()-> Int,
    selectedCurrency: UsersCurrency,
    onCurrencySelection : (UsersCurrency) -> Unit
){

    var isCurrencyDropDownExpanded by remember {
        mutableStateOf(false)
    }
    var balanceState by remember{ mutableStateOf(0) }
    balanceState = balance.invoke()

    Card(
        onClick = {},
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 5.dp, top = 5.dp)

    ) {
        Column(
            modifier =
            Modifier.background(
                Brush.horizontalGradient(
                    0.0f to Color(0xFF686868),
                    1f to Color(0xFF4E4E4E)
                )
                )){
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .paddingFromBaseline(bottom = 20.dp)
                ) {
                    FilledTonalButton(
                        onClick = {
                            isCurrencyDropDownExpanded = true
                        },
                        shape = RoundedCornerShape(50),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(vertical = 6.dp, horizontal = 9.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                red = MaterialTheme.colorScheme.primary.red,
                                green = MaterialTheme.colorScheme.primary.green,
                                blue = MaterialTheme.colorScheme.primary.blue,
                                alpha = 1/4f
                            )),
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp)
                    ) {

                        CurrencySelectionMenu(
                            isCurrencyDropDownExpanded = isCurrencyDropDownExpanded,
                            onDismissRequest = { isCurrencyDropDownExpanded = false },
                            onCurrencySelection = {onCurrencySelection.invoke(it)}
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = selectedCurrency.text,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.Bold
                            )
                            var showFlag by remember{ mutableStateOf(true) }
                            if(showFlag){
                                SubcomposeAsyncImage(model = selectedCurrency.iconURL,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    error = {
                                        showFlag = false
                                    },
                                    loading = {
                                        CircularProgressIndicator(
                                            strokeWidth = 2.dp,
                                            modifier = Modifier.size(10.dp)
                                        )
                                    },
                                    modifier = Modifier.defaultMinSize(0.dp,0.dp)
                                )
                            }

                        }

                    }
                    FilledIconButton(onClick = { },
                        shape = RoundedCornerShape(100),
                        colors = IconButtonDefaults
                            .filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_more_horiz_24),
                            contentDescription = null)
                    }

                }

                Column {
                    val transition = updateTransition(targetState = balanceState, label = "DigitTransition")

                    val digit = transition.animateInt(
                        label = "Digit",
                        transitionSpec = { tween(durationMillis = 1500, easing = LinearOutSlowInEasing) }
                    ) { state ->
                        state
                    }

                    Text(
                        text = "Your Balance",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary)
                    AnimatedContent(targetState = digit, label = "digit"
                    ) {value:State<Int> ->
                        Text(
                            text = "${selectedCurrency.symbols}${value.value}.00",
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.primary
                            )
                    }
                }


            }
        }


    }
}