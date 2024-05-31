package com.app.spendr.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun TabBar(
    FirstTabImg: String ,
    FirstTabText: String,
    SecondTabImg: String,
    SecondTabText: String,
    onStatisticsSection: (Boolean) -> Unit

){
    val height = 40.dp
    val widthWhenDisabled = 60.dp
    val widthWhenEnabled = 100.dp


    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        TabItem(painterResource(DrawableResource(path = FirstTabImg)), FirstTabText),
        TabItem(painterResource(DrawableResource(path = SecondTabImg)), SecondTabText))

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .width(widthWhenDisabled + widthWhenEnabled)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Tab(selected = selectedTabIndex == 0,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            onClick = {
                selectedTabIndex = 0
                onStatisticsSection.invoke(false)
                      },
            modifier =
            if (selectedTabIndex == 0){
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .height(height)
                    .width(widthWhenEnabled)
                    .background(MaterialTheme.colorScheme.scrim)
            }else{
                Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .height(height)
                    .width(widthWhenDisabled)
            }
        ) {
            if (selectedTabIndex == 0){
                Text(text = tabs[0].text)
            }else{
                Icon(tabs[0].icon, contentDescription = null )
            }
        }

        Tab(selected = selectedTabIndex == 1,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            onClick = {
                selectedTabIndex = 1
                onStatisticsSection.invoke(true)
                      },
            modifier =
            if (selectedTabIndex == 1){
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .width(widthWhenEnabled)
                    .height(height)
                    .background(MaterialTheme.colorScheme.scrim)
            }else{
                Modifier
                    .clip(
                        RoundedCornerShape(
                            topEnd = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .height(height)
                    .width(widthWhenDisabled)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            }
        ){
            if (selectedTabIndex == 1){
                Text(text = tabs[1].text)
            }else{
                Icon(tabs[1].icon, contentDescription = null )
            }
        }
    }
}

data class TabItem(val icon: Painter, val text: String)