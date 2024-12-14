package us.elopez.weathertracker.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import us.elopez.weathertracker.ui.theme.AppBlack

@Composable
fun HomeScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val searchResult by viewModel.searchResult.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Search Bar
            SearchBar(
                query = uiState.query,
                onQueryChange = { viewModel.updateQuery(it) },
                onSearch = { viewModel.searchWeather(it) },
                modifier = Modifier.fillMaxWidth()
            )

            // City Result Card (only visible if there’s a search result)
            searchResult?.let { result ->
                CityResultCard(
                    weatherData = result,
                    onCitySelected = { selectedCity ->
                        viewModel.selectCity(selectedCity)
                    }
                )
            }
        }

        // Main Content: Centered below the search bar
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.loading -> {
                    ShimmerWeatherCard()
                }
                /*uiState.error != null -> {
                    Text(text = uiState.error!!, color = Color.Red)
                }*/
                searchResult != null -> {
                    // Do nothing if search results are being displayed
                }
                uiState.weatherData != null -> {
                    WeatherCard(weather = uiState.weatherData!!)
                }
                else -> {
                    NoCitySelected()
                }
            }
        }
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