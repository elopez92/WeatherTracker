package us.elopez.weathertracker.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import us.elopez.weathertracker.data.api.WeatherData
import us.elopez.weathertracker.data.datastore.PreferencesManager
import us.elopez.weathertracker.data.repository.WeatherRepository
import us.elopez.weathertracker.domain.usecase.GetWeatherUseCase
import us.elopez.weathertracker.domain.usecase.SaveCityUseCase
import us.elopez.weathertracker.exceptions.WeatherExceptions
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    preferencesManager: PreferencesManager
) : ViewModel() {

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage

    private val _searchResult = MutableStateFlow<WeatherData?>(null)
    val searchResult: StateFlow<WeatherData?> = _searchResult.asStateFlow()

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        // Load saved city on app launch
        viewModelScope.launch {
            preferencesManager.selectedCity.collect { city ->
                if (city.isNullOrEmpty()) {
                    // No saved city, stop loading
                    _uiState.update { it.copy(loading = false) }
                } else {
                    // Load weather for the saved city
                    loadWeather(city)
                }
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
                _searchResult.value = weather
            } catch (e: WeatherExceptions.InvalidApiKeyException) {
                _searchResult.value = null
                _uiState.update { it.copy(error = "Invalid API Key. Please check your settings.") }
                _toastMessage.emit("Invalid API Key. Please check your settings.")
            } catch (e: WeatherExceptions.CityNotFoundException) {
                _searchResult.value = null
                _uiState.update { it.copy(error = "City not found. Please try a different location.") }
                _toastMessage.emit("City not found. Please try a different location.")
            } catch (e: WeatherExceptions.NetworkException) {
                _searchResult.value = null
                _uiState.update { it.copy(error = "Network error: ${e.message}") }
                _toastMessage.emit("Network error: ${e.message}")
            } catch (e: Exception) {
                _searchResult.value = null
                _uiState.update { it.copy(error = "An unexpected error occurred.") }
                _toastMessage.emit("An unexpected error occurred.")
            }
        }
    }

    // Selects the city from the search result and updates the main city weather
    fun selectCity(weatherData: WeatherData) {
        viewModelScope.launch {
            try {
                saveCityUseCase(weatherData.cityName) // Persist the city name
                _uiState.update { it.copy(weatherData = weatherData, error = null) }
                _searchResult.value = null // Clear the search result after selection
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to save selected city") }
            }
        }
    }

    private fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(loading = true) }
                val weather = getWeatherUseCase(city)
                _uiState.update { it.copy(weatherData = weather, loading = false) }
            } catch (e: WeatherExceptions.InvalidApiKeyException) {
                _uiState.update { it.copy(error = "Invalid API Key. Please check your settings.") }
                _toastMessage.emit("Invalid API Key. Please check your settings.")
            } catch (e: WeatherExceptions.CityNotFoundException) {
                _uiState.update { it.copy(error = "City not found. Please try a different location.") }
                _toastMessage.emit("City not found. Please try a different location.")
            } catch (e: WeatherExceptions.NetworkException) {
                _uiState.update { it.copy(error = "Network error: ${e.message}") }
                _toastMessage.emit("Network error: ${e.message}")
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "An unexpected error occurred.") }
                _toastMessage.emit("An unexpected error occurred.")
            }
        }
    }
}

data class WeatherUiState(
    val query: String = "",
    val weatherData: WeatherData? = null,
    val error: String? = null,
    val loading: Boolean = true
)
