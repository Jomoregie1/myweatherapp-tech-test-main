package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  @GetMapping("/daylight")
  public ResponseEntity<String> compareDaylightHours(@RequestParam String city1, @RequestParam String city2) {
    try {
      CityInfo cityInfo1 = weatherService.forecastByCity(city1);
      CityInfo cityInfo2 = weatherService.forecastByCity(city2);

      long daylightHours1 = calculateDaylightHours(cityInfo1.getCurrentConditions().getSunrise(), cityInfo1.getCurrentConditions().getSunset());
      long daylightHours2 = calculateDaylightHours(cityInfo2.getCurrentConditions().getSunrise(), cityInfo2.getCurrentConditions().getSunset());

      if (daylightHours1 > daylightHours2) {
        return ResponseEntity.ok(city1 + " has longer daylight hours.");
      } else if (daylightHours2 > daylightHours1) {
        return ResponseEntity.ok(city2 + " has longer daylight hours.");
      } else {
        return ResponseEntity.ok("Both cities have the same daylight hours.");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
  }


  @GetMapping("/raincheck")
  public ResponseEntity<String> checkRain(@RequestParam String city1, @RequestParam String city2) {
    try {
      CityInfo cityInfo1 = weatherService.forecastByCity(city1);
      CityInfo cityInfo2 = weatherService.forecastByCity(city2);

      boolean isRainingInCity1 = cityInfo1.getCurrentConditions().getConditions().contains("Rain");
      boolean isRainingInCity2 = cityInfo2.getCurrentConditions().getConditions().contains("Rain");


      if (isRainingInCity1 && isRainingInCity2) {
        return ResponseEntity.ok("It is raining in both " + city1 + " and " + city2 + ".");
      } else if (isRainingInCity1) {
        return ResponseEntity.ok("It is raining in " + city1 + ".");
      } else if (isRainingInCity2) {
        return ResponseEntity.ok("It is raining in " + city2 + ".");
      } else {
        return ResponseEntity.ok("It is not raining in either city.");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
  }

  // This helper function will calculate the number of daylight hours based on sunrise and sunset
  private long calculateDaylightHours(String sunrise, String sunset) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    Date date1 = format.parse(sunrise);
    Date date2 = format.parse(sunset);
    long difference = date2.getTime() - date1.getTime();
    return difference / (60 * 60 * 1000) % 24;
  }


}
