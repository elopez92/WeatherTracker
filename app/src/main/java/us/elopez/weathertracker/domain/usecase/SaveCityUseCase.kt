package us.elopez.weathertracker.domain.usecase

import us.elopez.weathertracker.data.repository.WeatherRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String) {
        repository.saveCity(city)
    }
}