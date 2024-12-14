package us.elopez.weathertracker.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import us.elopez.weathertracker.data.api.WeatherData
import kotlin.math.roundToInt

@Composable
fun CityResultCard(
    weatherData: WeatherData,
    onCitySelected: (WeatherData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCitySelected(weatherData) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = weatherData.cityName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(text = "${weatherData.temperature.roundToInt()}°", fontSize = 48.sp, fontWeight = FontWeight.Bold)
            }
            // Weather Icon on the Right
            Image(
                painter = rememberAsyncImagePainter("https:${weatherData.iconUrl}"), // Prepend "https:" to the icon URL
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}