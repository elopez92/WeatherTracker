# Weather Tracker App

A simple weather tracker app built with **Jetpack Compose**, **Kotlin**, and **MVVM architecture**. This app fetches weather data from [WeatherAPI.com](https://www.weatherapi.com/) and displays it for a selected city.

## Features

- Search for a city and view its current weather details.
- Persist the selected city across app launches.
- Display weather information such as:
  - Temperature
  - Humidity
  - UV Index
  - Feels-like temperature
  - Weather condition with an icon
- Clean architecture and modular design.

---

## Setup Instructions

To run this project, you'll need to set up your own WeatherAPI key.

### Step 1: Get an API Key
1. Go to [WeatherAPI.com](https://www.weatherapi.com/) and create an account.
2. Obtain a free API key from the WeatherAPI dashboard.

### Step 2: Add the API Key to the Project
1. Create a file named `apikeys.properties` in the root of the project.
2. Add the following line to the file:
   ```properties
   WEATHER_API_KEY=your_api_key_here
