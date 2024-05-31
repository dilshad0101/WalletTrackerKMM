package com.app.spendr.presentation.editor

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chips(text: String,isEnabled: Boolean, onClick: ()-> Unit){
    FilterChip(
        selected = isEnabled,
        onClick = onClick,
        label = { Text(text) },
        shape = RoundedCornerShape(20),
        colors = FilterChipDefaults
            .filterChipColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                labelColor = MaterialTheme.colorScheme.tertiary,
                iconColor = MaterialTheme.colorScheme.tertiary,
            ),
        trailingIcon = {
            if(isEnabled){
                Icon(Icons.Default.Close,null)
            }
        },

    )
}
