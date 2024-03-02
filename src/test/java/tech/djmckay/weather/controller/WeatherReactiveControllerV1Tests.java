package tech.djmckay.weather.controller;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.service.impl.WeatherServiceImpl;

@WebMvcTest(WeatherReactiveControllerV1.class)
@AutoConfigureMockMvc
class WeatherReactiveControllerV1Tests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    private static String WEATHER_URL = "/v1/weather/today";

    @BeforeEach
    public void setup() {
    	Forecast daily = new Forecast();
    	daily.setName("Monday");
    	daily.setTempHighInCelsius(27.2);
    	daily.setForecastBlurp("Partly Sunny");
        WeatherResponse dailyWeatherResponse = new WeatherResponse();
        dailyWeatherResponse.setDaily(new ArrayList<Forecast>());
        dailyWeatherResponse.getDaily().add(daily);

        when(weatherService.getToday()).thenReturn(Mono.just(dailyWeatherResponse));
    }

    @Test
    public void getWeatherSuccessfulTest() throws Exception {
        mockMvc.perform(get(WEATHER_URL))
                .andExpect(status().is2xxSuccessful());
        }
}
