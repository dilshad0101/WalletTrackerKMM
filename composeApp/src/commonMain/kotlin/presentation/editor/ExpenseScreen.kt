package com.app.spendr.presentation.editor


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Transaction
import com.app.spendr.presentation.TopBar
import com.app.spendr.presentation.home.UsersCurrency
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import navigation.Screen


// declaring variables globally looks ugly even though this approach for implementation of expense works fine.
// if u found an alternative approach PLEASE do it.
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpenseScreen(navController: NavController,
                  onSave: (Transaction) -> Unit,
                  savedCurrency: UsersCurrency,
                  ){
    Scaffold(containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) { it ->

        val date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        var contentTextField by remember{
            mutableStateOf(TextFieldValue(""))
        }
        var amountTextField by remember{
            mutableStateOf(TextFieldValue("")) }

        var enabledChip by remember{
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(it)
        ){
            //val context = LocalContext.current
            TopBar(
                onFirstBtnClick = { navController.navigate(Screen.MainScreen.route) },
                firstBtnIcon = "baseline_arrow_back_24.xml",
                onSecondBtnClick = {
                    if(contentTextField.text.isNotBlank() &&
                       // amountTextField.text.isDigitsOnly() &&
                        amountTextField.text.isNotBlank()){
                        onSave.invoke(
                            Transaction(
                                title = contentTextField.text,
                                description = enabledChip.ifBlank { Description.Other.text },
                                amount = amountTextField.text.toInt(),
                                id = 0,
                                isSavings = false,
                                date = date
                            )
                        )
                    }else{
                        //TOAST Is not Supported yet
                        //Toast.makeText(context,"Invalid Input",Toast.LENGTH_SHORT).show()

                    }

                },
                secondBtnIcon = R.drawable.baseline_save_24
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Add New\nExpense To Your\nRecord",
                minLines = 2,
                maxLines = 3,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "To",
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = contentTextField,
                onValueChange = {
                    contentTextField = it },
                textStyle = MaterialTheme.typography.labelMedium,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary


                ),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(vertical = 8.dp)){
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    ) {
                    (Description.descriptions).forEach { text:String ->
                        Chips(text = text, isEnabled = enabledChip == text) {
                            enabledChip = if (enabledChip == text) {
                                ""
                            } else {
                                text
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Amount",
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = amountTextField,
                onValueChange = {
                    amountTextField = it },
                textStyle = MaterialTheme.typography.labelMedium,
                prefix = {
                    Text(
                        text = savedCurrency.symbols,
                        color = MaterialTheme.colorScheme.primary,)
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedTextColor = MaterialTheme.colorScheme.primary
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()

            )

        }
}
}