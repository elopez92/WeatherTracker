package us.elopez.weathertracker.domain.usecase

import us.elopez.weathertracker.data.api.WeatherData
import us.elopez.weathertracker.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): WeatherData {
        return repository.getWeather(city)
    }
}