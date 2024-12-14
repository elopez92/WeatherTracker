package us.elopez.weathertracker.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import us.elopez.weathertracker.data.api.WeatherData

@Composable
fun WeatherCard(weather: WeatherData) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = weather.cityName, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Temp: ${weather.temperature}°C")
            Text(text = "Condition: ${weather.condition}")
            Text(text = "Feels Like: ${weather.feelsLike}°C")
            Text(text = "Humidity: ${weather.humidity}%")
            Text(text = "UV Index: ${weather.uvIndex}")
        }
    }
}