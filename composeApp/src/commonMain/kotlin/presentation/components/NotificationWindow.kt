package com.app.spendr.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

// In this app we wont be sending notifications to specific users
// instead we use it to broadcast to all users
// for testing purpose list of notification is now static
// we will be requesting list of notification from Api of original author's portfolio website(available in github)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationWindow(
    onDismissRequest : () -> Unit
){
    Popup(
        alignment = Alignment.TopEnd,
        onDismissRequest = onDismissRequest
    ){
        Box(
            modifier = Modifier
                .heightIn(min = 300.dp, max = 600.dp)
                .width(200.dp)
                .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(10))
                .padding(vertical = 10.dp, horizontal = 5.dp)

        ){
            val list = listOf<String>(
                "Say Hii to new Notification Section"
                )



            LazyColumn(){

                item{

                    Text(
                        text = "Notifications",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 15.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally))
                }

                this.itemsIndexed(list){n: Int,text: String ->
                    Card(
                        onClick = {},
                        elevation = CardDefaults.cardElevation(0.dp),
                        modifier = Modifier.padding(bottom = 10.dp)
                    ){
                        Row(
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            Icon(
                                Icons.Filled.MoreVert,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )

                            Column(
                            ) {
                                Text(
                                    text = text,
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodySmall,
                                    softWrap = true,
                                    fontWeight = FontWeight.Normal
                                )
                                Spacer(modifier = Modifier
                                    .height(5.dp)
                                    .fillMaxWidth())

                                if (n+1 != list.size){
                                    Spacer(
                                        modifier = Modifier
                                            .height(0.3.dp)
                                            .fillMaxWidth()
                                            .background(
                                                Color.Gray
                                            )
                                    )
                                }

                            }
                        }
                    }




                }
            }
        }
    }

}