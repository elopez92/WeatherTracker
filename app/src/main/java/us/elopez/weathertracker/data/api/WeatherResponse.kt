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
            uvIndex = current.uv,
            iconUrl = current.condition.icon,
            windDirection = current.wind_degree,
            windSpeed = current.wind_kph
        )
    }
}

data class Location(val name: String)
data class Current(
    val temp_c: Double,
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val uv: Double,
    val wind_degree: Float,
    val wind_kph: Float,
)
data class Condition(
    val text: String,
    val icon: String
)
data class WeatherData(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val feelsLike: Double,
    val humidity: Int,
    val uvIndex: Double,
    val iconUrl: String,
    val windDirection: Float, // Wind direction in degrees
    val windSpeed: Float      // Wind speed in km/h or mph
)