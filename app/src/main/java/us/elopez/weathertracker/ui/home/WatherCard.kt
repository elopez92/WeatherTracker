package us.elopez.weathertracker.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import us.elopez.weathertracker.R
import us.elopez.weathertracker.data.api.WeatherData
import us.elopez.weathertracker.ui.theme.AppDarkGray
import us.elopez.weathertracker.ui.theme.AppGray
import kotlin.math.roundToInt

@Composable
fun WeatherCard(weather: WeatherData) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Weather Icon on the Right
            Image(
                painter = rememberAsyncImagePainter("https:${weather.iconUrl}"), // Prepend "https:" to the icon URL
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = weather.cityName, style = MaterialTheme.typography.headlineMedium)
                WindDirectionArrow(degrees = weather.windDirection)
            }
            Text(
                text = " ${weather.temperature.roundToInt()}°",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier.padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = AppGray
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly // Space sections evenly
                ) {
                    WeatherInfoSection("Humidity", "${weather.humidity}%")
                    WeatherInfoSection("UV Index", weather.uvIndex.roundToInt().toString())
                    WeatherInfoSection("Feels Like", "${weather.feelsLike.roundToInt()}°")
                }
            }

        }

}

@Composable
fun WeatherInfoSection(header: String, info: String) {
    Column(
        modifier = Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = header,
            color = Color(0xFFC4C4C4),
            fontSize = if (header == "Feels Like") 12.sp else 16.sp)
        Text(
            text = info,
            modifier = Modifier.padding(top = 8.dp),
            color = AppDarkGray)
    }
}

@Composable
fun WindDirectionArrow(degrees: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow), // Replace with your drawable name
            contentDescription = "Wind Direction",
            modifier = Modifier
                .size(24.dp) // Arrow size
                .rotate(degrees-45), // Rotate based on wind direction
        )
    }
}
