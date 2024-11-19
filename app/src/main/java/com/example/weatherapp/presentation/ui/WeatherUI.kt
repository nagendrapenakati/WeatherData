package com.example.weatherapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.util.NetWorkResponse
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import kotlin.math.sin

@Composable
fun WeatherUI(viewModel: WeatherViewModel) {

    val weatherData = viewModel.liveData.observeAsState()

    var searchCity by remember { mutableStateOf("") }

    if (searchCity.isEmpty()) {
        viewModel.getWeather("New Jersey")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

    OutlinedTextField(
        value = searchCity,
        onValueChange = {
            newVal->
            searchCity=newVal
            viewModel.getWeather(searchCity)
        },
        label = { Text("Search City") },
        placeholder = { Text("Enter City Name") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(bottom =20.dp)
    )



    when(val res = weatherData.value){
        is NetWorkResponse.Error ->
            Text(
            text = "Error fetching data. Please try again later.",
            color = Color.Red,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        NetWorkResponse.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        is NetWorkResponse.success -> WeatherPage(res.data)
        null -> Text(
            text = "No weather data available.",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
    }
}

@Composable
fun WeatherPage(item: WeatherModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Weather in ${item.name}",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Description: ${item.weather.firstOrNull()?.description ?: "N/A"}",
            modifier = Modifier.padding(top = 8.dp)
        )

        // Display temperature
        Text(
            text = "Temperature: ${item.main.temp}°C",
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Max Temp: ${item.main.temp_max}°C | Min Temp: ${item.main.temp_min}°C",
            modifier = Modifier.padding(top = 4.dp)
        )

        // Display humidity and other details
        Text(
            text = "Humidity: ${item.main.humidity}%",
            modifier = Modifier.padding(top = 4.dp)
        )

        // Optionally display wind speed and other weather-related information
        Text(
            text = "Wind Speed: ${item.wind.speed} m/s",
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}
