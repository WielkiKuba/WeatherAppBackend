# 🌤️ WeatherAppBackend

A reactive Spring Boot backend service that provides weather forecasts and summaries using geographical coordinates. This application fetches data from an external weather API and exposes multiple endpoints to deliver current, daily, weekly weather, and summary statistics.

## 🚀 Technologies Used

- Java + Spring Boot (WebFlux)
- Project Reactor (Mono)
- External weather API (via WebClient)
- RESTful endpoints

## 📦 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/WielkiKuba/WeatherAppBackend.git
   cd WeatherAppBackend
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Default Port**: `http://localhost:8080`

---

## 🌐 API Endpoints

All endpoints accept `latitude` and `longitude` as **required** query parameters. Both must be valid decimal values:
- Latitude: from -90 to 90
- Longitude: from -180 to 180

If values are out of bounds, a `400 BAD REQUEST` is returned.

---

### `GET /weather`

**Description**: Get raw weather data for the provided coordinates.

**Query Parameters**:
- `latitude` (double)
- `longitude` (double)

**Response**: JSON object containing hourly and daily weather data:
```json
{
  "latitude": 52,
  "longitude": 21,
  "daily": {...},
  "hourly": {
    "surfacePressure": [1013.25, 1012.8, ...]
  }
}
```

---

### `GET /weather/daily`

**Description**: Get weather data for the current day.

**Query Parameters**:
- `latitude` (double)
- `longitude` (double)

**Response**: Object of type `Day`:
```json
{
  "date": "2025-06-17",
  "temperatureMax": 25.3,
  "temperatureMin": 15.2,
  "weatherCode": 61,
  "sunshineDuration": 18000,
  "estimatedEnergy": 25.0
}
```

---

### `GET /weather/weekly`

**Description**: Get weather forecast for the upcoming week.

**Query Parameters**:
- `latitude` (double)
- `longitude` (double)

**Response**: List of `Day` objects.

---

### `GET /weather/summary`

**Description**: Get summary statistics for the week's weather.

**Query Parameters**:
- `latitude` (double)
- `longitude` (double)

**Response**: Object of type `Summary`:
```json
{
  "avgPressure": 1012.6,
  "avgSunshineDuration": 16000,
  "maxTemp": 27.8,
  "minTemp": 14.1,
  "rainy": true
}
```

---

## 🔧 Error Handling

- `400 BAD REQUEST`: invalid latitude or longitude values
- External API errors are logged and handled gracefully with descriptive messages:
  - Invalid request to external API
  - Server error from API
  - No connection / I/O failure

---

## 📬 Example Request

```http
GET /weather/summary?latitude=52.23&longitude=21.01 HTTP/1.1
Host: localhost:8080
```

---

## 📁 Project Structure Highlights

- `WeatherController.java` – REST endpoints
- `WeatherService.java` – Logic + external API calls
- `Day`, `Summary`, `Weather` – Data models
- Uses WebFlux & `Mono<>` for reactive, async responses

---

## 📃 License

This project is for educational and demonstrational purposes.
