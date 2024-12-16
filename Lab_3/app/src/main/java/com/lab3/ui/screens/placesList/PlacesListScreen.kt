package com.lab3.ui.screens.placesList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab3.data.ItemsData

@Composable
fun PlacesListScreen(
    onDetailsScreen: (Int) -> Unit,
) {
    val itemsListState = remember { mutableStateOf(ItemsData.itemsList) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Туристичні місця Норвегії",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 10.dp)
                .padding(top = 18.dp)
                .padding(horizontal = 18.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(itemsListState.value) { item ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.DarkGray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable
                        {
                            onDetailsScreen(item.id)
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(){

                                Image(
                                    painter = painterResource(id = item.image),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    modifier = Modifier.padding(top = 1.dp, bottom = 0.dp),
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        {
                                            append(item.name)
                                        }
                                        append("\n")
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 16.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Normal
                                            )
                                        )
                                        {
                                            append(item.title)

                                        }

                                    }
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlacesListScreenPreview() {
    PlacesListScreen({})
}
