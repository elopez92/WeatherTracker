package us.elopez.weathertracker.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import us.elopez.weathertracker.ui.theme.AppBlack

@Composable
fun HomeScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Search Bar
        SearchBar(
            query = uiState.query,
            onQueryChange = { viewModel.updateQuery(it) },
            onSearch = { viewModel.searchWeather(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weather Info or Placeholder
        uiState.weatherData?.let { weather ->
            WeatherCard(weather = weather)
        } ?: NoCitySelected()
    }
}

@Composable
fun NoCitySelected(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No City Selected", style = MaterialTheme.typography.headlineLarge, color = AppBlack, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Please Search For A City", style = MaterialTheme.typography.bodyLarge, color = AppBlack, fontWeight = FontWeight.Bold)
        }
    }
}