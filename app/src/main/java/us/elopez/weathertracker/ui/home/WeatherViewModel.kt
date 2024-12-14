package us.elopez.weathertracker.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import us.elopez.weathertracker.data.api.WeatherData
import us.elopez.weathertracker.data.datastore.PreferencesManager
import us.elopez.weathertracker.data.repository.WeatherRepository
import us.elopez.weathertracker.domain.usecase.GetWeatherUseCase
import us.elopez.weathertracker.domain.usecase.SaveCityUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    preferencesManager: PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        // Load saved city on app launch
        viewModelScope.launch {
            preferencesManager.selectedCity.collect { city ->
                city?.let { loadWeather(it) }
            }
        }
    }

    fun updateQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun searchWeather(city: String) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)
                _uiState.update { it.copy(weatherData = weather) }
                saveCityUseCase(city) // Persist city
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "City not found or network error") }
            }
        }
    }

    private fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                val weather = getWeatherUseCase(city)
                _uiState.update { it.copy(weatherData = weather) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to load weather data") }
            }
        }
    }
}

data class WeatherUiState(
    val query: String = "",
    val weatherData: WeatherData? = null,
    val error: String? = null
)
