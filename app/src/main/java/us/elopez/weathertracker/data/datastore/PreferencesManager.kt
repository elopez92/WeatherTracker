package us.elopez.weathertracker.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val SELECTED_CITY_KEY = stringPreferencesKey("selected_city")

    val selectedCity: Flow<String?> = dataStore.data.map { prefs ->
        prefs[SELECTED_CITY_KEY]
    }

    suspend fun saveSelectedCity(city: String) {
        dataStore.edit { prefs -> prefs[SELECTED_CITY_KEY] = city }
    }
}
