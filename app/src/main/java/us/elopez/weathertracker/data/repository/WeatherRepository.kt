package us.elopez.weathertracker.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import us.elopez.weathertracker.data.api.WeatherApi
import us.elopez.weathertracker.data.api.WeatherData
import javax.inject.Inject

 interface WeatherRepository {
     suspend fun getWeather(city: String): WeatherData
     suspend fun saveCity(city: String)
     suspend fun getSavedCity(): String?
 }

