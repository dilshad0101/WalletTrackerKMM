package com.app.spendr.presentation.editor

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.app.spendr.R
import com.app.spendr.data.Transaction
import com.app.spendr.presentation.TopBar
import com.app.spendr.presentation.home.UsersCurrency
import com.app.spendr.presentation.navigation.Screen
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SavingsScreen(navController: NavController,
                  onSave: (Transaction) -> Unit,
                  savedCurrency: UsersCurrency
){
    Scaffold(containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
        ) {

        val date = LocalDate.now()

        BackHandler(true) {
            navController.navigate(Screen.MainScreen.route)
        }
    var contentTextField by remember{
        mutableStateOf(TextFieldValue(""))}
    var amountTextField by remember{
        mutableStateOf(TextFieldValue("")) }

        var enabledChip by remember {
            mutableStateOf("")
        }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp).padding(it)
    ){
        val context = LocalContext.current

        TopBar(
            onFirstBtnClick = { navController.navigate(Screen.MainScreen.route) },
            firstBtnIcon = R.drawable.baseline_arrow_back_24,
            onSecondBtnClick = {
                if(contentTextField.text.isNotBlank() && amountTextField.text.isDigitsOnly() && amountTextField.text.isNotBlank()){

                    onSave.invoke(
                        Transaction(
                            title = contentTextField.text,
                            description = enabledChip.ifBlank { Description.Other.text },
                            amount = amountTextField.text.toInt(),
                            id = 0,
                            isSavings = true,
                            date = date
                        )
                    )
                }else{
                    Toast.makeText(context,"Invalid Input", Toast.LENGTH_SHORT).show()

                }

            },
            secondBtnIcon = R.drawable.baseline_save_24
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Add New\nSavings To Your\nRecord",
            minLines = 2,
            maxLines = 3,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
            )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "From",
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
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()

        )

    }
}
}