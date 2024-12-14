package us.elopez.weathertracker.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import us.elopez.weathertracker.data.api.WeatherApi
import us.elopez.weathertracker.data.datastore.PreferencesManager
import us.elopez.weathertracker.data.repository.WeatherRepository
import us.elopez.weathertracker.data.repository.WeatherRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        preferencesManager: DataStore<Preferences>
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi, preferencesManager)
    }
}