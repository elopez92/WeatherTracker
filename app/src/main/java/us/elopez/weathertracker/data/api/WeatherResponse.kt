package us.elopez.weathertracker.data.api

data class WeatherResponse(
    val location: Location,
    val current: Current
) {
    fun toWeatherData(): WeatherData {
        return WeatherData(
            cityName = location.name,
            temperature = current.temp_c,
            condition = current.condition.text,
            feelsLike = current.feelslike_c,
            humidity = current.humidity,
            uvIndex = current.uv
        )
    }
}

data class Location(val name: String)
data class Current(
    val temp_c: Double,
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val uv: Double
)
data class Condition(val text: String)
data class WeatherData(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val feelsLike: Double,
    val humidity: Int,
    val uvIndex: Double
)