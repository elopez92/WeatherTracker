package us.elopez.weathertracker.exceptions

class WeatherExceptions {
    // Exception for an invalid API key
    class InvalidApiKeyException : Exception("Invalid API Key")

    // Exception for a city not being found
    class CityNotFoundException : Exception("City not found")

    // General exception for network-related issues
    class NetworkException(message: String) : Exception(message)
}