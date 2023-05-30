package com.weatherapp.myweatherapp;

import com.weatherapp.myweatherapp.controller.WeatherController;
import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyweatherappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@Test
	void contextLoads() {
	}
	@Test
	public void compareDaylightHours() throws Exception {
		CityInfo cityInfo1 = new CityInfo();
		CityInfo.CurrentConditions currentConditions1 = new CityInfo.CurrentConditions();
		currentConditions1.setSunrise("06:00");
		currentConditions1.setSunset("20:00");
		currentConditions1.setConditions("Rain");
		cityInfo1.setCurrentConditions(currentConditions1);

		CityInfo cityInfo2 = new CityInfo();
		CityInfo.CurrentConditions currentConditions2 = new CityInfo.CurrentConditions();
		currentConditions2.setSunrise("07:00");
		currentConditions2.setSunset("21:00");
		currentConditions2.setConditions("Clear");
		cityInfo2.setCurrentConditions(currentConditions2);

		when(weatherService.forecastByCity("london")).thenReturn(cityInfo1);
		when(weatherService.forecastByCity("barcelona")).thenReturn(cityInfo2);

		mockMvc.perform(get("/daylight?city1=london&city2=barcelona")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void checkRain() throws Exception {
		CityInfo cityInfo1 = new CityInfo();
		CityInfo.CurrentConditions currentConditions1 = new CityInfo.CurrentConditions();
		currentConditions1.setSunrise("06:00");
		currentConditions1.setSunset("20:00");
		currentConditions1.setConditions("Rain");
		cityInfo1.setCurrentConditions(currentConditions1);

		CityInfo cityInfo2 = new CityInfo();
		CityInfo.CurrentConditions currentConditions2 = new CityInfo.CurrentConditions();
		currentConditions2.setSunrise("07:00");
		currentConditions2.setSunset("21:00");
		currentConditions2.setConditions("Clear");
		cityInfo2.setCurrentConditions(currentConditions2);

		when(weatherService.forecastByCity("london")).thenReturn(cityInfo1);
		when(weatherService.forecastByCity("barcelona")).thenReturn(cityInfo2);

		mockMvc.perform(get("/raincheck?city1=london&city2=barcelona")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("It is raining in london."));
	}

	@Test
	public void checkRain_BothCitiesRaining() throws Exception {
		CityInfo cityInfo1 = new CityInfo();
		CityInfo.CurrentConditions currentConditions1 = new CityInfo.CurrentConditions();
		currentConditions1.setConditions("Rain");
		cityInfo1.setCurrentConditions(currentConditions1);

		CityInfo cityInfo2 = new CityInfo();
		CityInfo.CurrentConditions currentConditions2 = new CityInfo.CurrentConditions();
		currentConditions2.setConditions("Rain");
		cityInfo2.setCurrentConditions(currentConditions2);

		when(weatherService.forecastByCity("london")).thenReturn(cityInfo1);
		when(weatherService.forecastByCity("barcelona")).thenReturn(cityInfo2);

		mockMvc.perform(get("/raincheck?city1=london&city2=barcelona")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("It is raining in both london and barcelona."));
	}

	@Test
	public void compareDaylightHours_ServiceFails() throws Exception {
		// Setup
		when(weatherService.forecastByCity(anyString())).thenThrow(new RuntimeException("Service Failure"));

		mockMvc.perform(get("/daylight?city1=london&city2=barcelona")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("An error occurred: Service Failure")));
	}

	@Test
	public void checkRain_ServiceFails() throws Exception {
		// Setup
		when(weatherService.forecastByCity(anyString())).thenThrow(new RuntimeException("Service Failure"));

		mockMvc.perform(get("/raincheck?city1=london&city2=barcelona")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("An error occurred: Service Failure")));
	}

}

