package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  private String address;

  @JsonProperty("description")
  private String description;

  @JsonProperty("currentConditions")
  public CurrentConditions currentConditions;

  @JsonProperty("days")
  private List<Days> days;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public void setCurrentConditions(CurrentConditions currentConditions) {
    this.currentConditions = currentConditions;
  }

  public List<Days> getDays() {
    return days;
  }

  public void setDays(List<Days> days) {
    this.days = days;
  }

  public static class CurrentConditions {
    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("feelslike")
    private String feelslike;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("conditions")
    private String conditions;

    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
      this.currentTemperature = currentTemperature;
    }

    public String getSunrise() {
      return sunrise;
    }

    public void setSunrise(String sunrise) {
      this.sunrise = sunrise;
    }

    public String getSunset() {
      return sunset;
    }

    public void setSunset(String sunset) {
      this.sunset = sunset;
    }

    public String getFeelslike() {
      return feelslike;
    }

    public void setFeelslike(String feelslike) {
      this.feelslike = feelslike;
    }

    public String getHumidity() {
      return humidity;
    }

    public void setHumidity(String humidity) {
      this.humidity = humidity;
    }

    public String getConditions() {
      return conditions;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }
  }

  static class Days {

    @JsonProperty("datetime")
    private String date;

    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("tempmax")
    private String maxTemperature;

    @JsonProperty("tempmin")
    private String minTemperature;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("description")
    private String description;

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
      this.currentTemperature = currentTemperature;
    }

    public String getMaxTemperature() {
      return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
      this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
      return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
      this.minTemperature = minTemperature;
    }

    public String getConditions() {
      return conditions;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }
}