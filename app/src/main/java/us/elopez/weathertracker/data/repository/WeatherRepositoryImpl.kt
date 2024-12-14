package us.elopez.weathertracker.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import us.elopez.weathertracker.BuildConfig
import us.elopez.weathertracker.data.api.WeatherApi
import us.elopez.weathertracker.data.api.WeatherData
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dataStore: DataStore<Preferences>
) : WeatherRepository {
    val apiKey = BuildConfig.WEATHER_API_KEY
    // Fetch weather data from the API
    override suspend fun getWeather(city: String): WeatherData {
        val response = weatherApi.getWeather(apiKey = apiKey, city = city)
        return response.toWeatherData() // Map API model to domain model
    }

    // Save the selected city in DataStore
    override suspend fun saveCity(city: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_CITY_KEY] = city
        }
    }

    // Retrieve the saved city from DataStore
    override suspend fun getSavedCity(): String? {
        return dataStore.data.map { preferences ->
            preferences[SELECTED_CITY_KEY]
        }.firstOrNull() // Return the first value emitted
    }

    companion object {
        private val SELECTED_CITY_KEY = stringPreferencesKey("selected_city")
    }
}
