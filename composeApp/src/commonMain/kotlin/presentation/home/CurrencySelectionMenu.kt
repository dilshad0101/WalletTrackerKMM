package com.app.spendr.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun CurrencySelectionMenu(
    isCurrencyDropDownExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onCurrencySelection: (UsersCurrency) -> Unit,
    ){
    val list = listOf(USD,POUND,EURO,INR)
    DropdownMenu(
        expanded = isCurrencyDropDownExpanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clip(RoundedCornerShape(10))
            .animateContentSize()
            .shadow(0.dp)

        ) {
        list.forEach {
            DropdownMenuItem(
                enabled = true,
                text = {
                       Text(text = it.text)
                },
                onClick = {onCurrencySelection.invoke(it)},
                colors = MenuDefaults
                    .itemColors(
                        textColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
            )
        }
    }
}