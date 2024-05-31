package com.app.spendr.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.spendr.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationCards(savingsButton: Boolean,onClick: ()-> Unit){

    val brush : Brush
    val text : String
    val icon : Int
    val tint : Color

    if(savingsButton){
        brush = Brush.linearGradient(
            0.0f to Color(0xFf6B6B6B),
            1f to Color(0x546C6C6C)
        );
        text = "Savings"
        icon = R.drawable.baseline_arrow_circle_down_24
        tint = MaterialTheme.colorScheme.primary
    }else{
        brush = Brush.linearGradient(
            0.0f to MaterialTheme.colorScheme.primary,
            1f to MaterialTheme.colorScheme.tertiary)
        text = "Expense"
        icon = R.drawable.baseline_arrow_circle_up_24
        tint = MaterialTheme.colorScheme.onPrimary

    }
    Card(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        shape = RoundedCornerShape(15),

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp)
                    .fillMaxHeight()
                ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = tint
                )

                Text(text = text,
                    style = MaterialTheme.typography.displaySmall,
                    color = tint)
            }

        }
    }
}