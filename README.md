# MyWeatherApp

## Overview

MyWeatherApp is a simple Spring Boot application that allows users to compare the weather conditions between two different cities. This application uses a third-party weather service to retrieve weather data and performs analysis on two major aspects:

1. **Daylight Hours Comparison:** Compares the daylight hours of two cities based on the sunrise and sunset times.

2. **Rain Check:** Checks if it's raining in either of the cities.

## Implementation Details

### Daylight Hours

The endpoint for comparing daylight hours is GET /daylight. This method takes two request parameters, city1 and city2, which are the names of the cities to compare. The application calls the weather service to retrieve the sunrise and sunset times for each city, calculates the number of daylight hours, and compares the results. The result is returned in the response.

```java
@GetMapping("/daylight")
public ResponseEntity<String> compareDaylightHours(@RequestParam String city1, @RequestParam String city2)
}
```

### Rain Check

The endpoint for checking rain is GET /raincheck. This method also takes city1 and city2 as request parameters. The application checks the current weather conditions for each city from the weather service and checks if it's raining. The result is returned in the response.

```java
@GetMapping("/raincheck")
public ResponseEntity<String> checkRain(@RequestParam String city1, @RequestParam String city2)

```

### Error Handling

In both cases, if an error occurs during the execution (e.g. the city name is wrong, or the weather service fails), the controller will catch the exception and return a 500 HTTP status code, along with a message indicating the error.

### Testing

Unit tests are implemented using JUnit 5 and Mockito to ensure that the implemented features work as expected.
The tests simulate different weather conditions by mocking the WeatherService's forecastByCity() method to return custom CityInfo objects.
For example, the compareDaylightHours() test case checks that the GET /daylight endpoint returns a successful status code and verifies the daylight comparison result.
The checkRain() test case checks the GET /raincheck endpoint and verifies whether it correctly identifies if it's raining in the cities.
Additional test cases simulate scenarios where both cities are experiencing rain and where the weather service fails to respond.
