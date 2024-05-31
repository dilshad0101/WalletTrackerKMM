package com.app.spendr.presentation.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.spendr.data.Transaction
import com.app.spendr.presentation.components.TransactionCard
import com.app.spendr.presentation.navigation.Screen
import com.app.spendr.presentation.stats.extractData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    transactionData: List<Transaction>,
    deleteTransaction : (Transaction) -> Unit,
    changePreference : (UsersCurrency) -> Unit,
    savedCurrency: UsersCurrency

){
    val activity = LocalContext.current as? Activity
    BackHandler(true) {
        activity?.finish()
    }



    Scaffold() { padding ->
        Column(modifier = Modifier
            .padding(padding)
        ) {
            BalanceCard(balance =  {
                var balance by mutableStateOf(0)
                balance = extractData(transactionData).balance
                return@BalanceCard balance
                                   },
                selectedCurrency = savedCurrency,
                onCurrencySelection = {
                    changePreference.invoke(it)
                }
                )
            Row(modifier = Modifier.padding(top = 8.dp)){
                NavigationCards(
                    savingsButton = true,
                    onClick = { navController.navigate(Screen.Savings.route) }
                )
                Spacer(modifier = Modifier.width(10.dp))

                NavigationCards(
                    savingsButton = false,
                    onClick = { navController.navigate(Screen.Expense.route) }
                    )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Your Transactions",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(visible = transactionData.isNotEmpty()) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = Modifier.animateContentSize()
                    ) {
                    this.items(items = transactionData.reversed()) {
                        TransactionCard(
                            title = it.title,
                            description = it.description,
                            amount = it.amount,
                            isSavings = it.isSavings,
                            onDelete = { deleteTransaction.invoke(it)},
                            savedCurrency = savedCurrency
                        )
                    }
                    item { Spacer(modifier = Modifier.height(10.dp)) }


                }
            }




        }
        }
}
