package us.elopez.weathertracker.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun ShimmerWeatherCard() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .placeholder(
                    visible = true,
                    color = Color.Gray.copy(alpha = 0.3f), // Base color
                    highlight = PlaceholderHighlight.shimmer(), // Shimmer color
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .placeholder(
                    visible = true,
                    color = Color.Gray.copy(alpha = 0.3f),
                    highlight = PlaceholderHighlight.shimmer(), // Shimmer color
                    shape = RoundedCornerShape(8.dp)
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = Color.Gray.copy(alpha = 0.3f),
                    highlight = PlaceholderHighlight.shimmer(), // Shimmer color
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}