package com.lab6.ui.screens.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab6.ui.components.WeatherMainCustomView
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastScreenViewModel = getViewModel()
) {
    val weatherForecastResponseState = viewModel.weatherForecastResponseStateFlow.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf(viewModel.cities.first()) }

    val firstElement = weatherForecastResponseState.value?.list?.first();
    val lastElement = weatherForecastResponseState.value?.list?.last();

    val startDateFormatted =  firstElement?.let {
        SimpleDateFormat("dd.MM.yyyy", Locale("uk")).format(Date(firstElement.dt * 1000))
    } ?: ""
    val endDateFormatted =  lastElement?.let {
        SimpleDateFormat("dd.MM.yyyy", Locale("uk")).format(Date(lastElement.dt * 1000))
    } ?: ""
    val screenTitle = "Прогноз погоди на $startDateFormatted – $endDateFormatted"

    Column(
        modifier = Modifier.fillMaxSize().padding(13.dp)
    ) {
        Text(
            text = screenTitle,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            Button(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF90CAF9),
                    disabledContentColor = Color(0xFFBBDEFB)
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Обране місто - $selectedCity",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Розгорнути",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(300.dp).background(Color(0xFF1976D2))
            ) {
                viewModel.cities.forEach { city ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCity = city
                            expanded = false
                            viewModel.fetchWeatherForecast(city)
                        },
                        text = {
                            Text(
                                text = city,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(weatherForecastResponseState.value?.list?: emptyList()) { weatherForecast ->
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = SimpleDateFormat("Час: dd MMMM yyyy року, HH:mm", Locale("uk")).format(Date(weatherForecast.dt * 1000)),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp, top = 8.dp)
                    )
                }
                WeatherMainCustomView(weatherMain = weatherForecast.main)
            }
        }
    }
}