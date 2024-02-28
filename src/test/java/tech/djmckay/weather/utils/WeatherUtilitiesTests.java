package tech.djmckay.weather.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

class WeatherUtilitiesTests {

	@Test
    public void convertToCelsiusTest() {
        Period forcast = new Period();
        forcast.setTemperature(80);
		String actual = WeatherUtilities.convertToCelsius(forcast);

        assertEquals("26.7", actual);
    }
	
	@Test
    public void convertToCelsiusInCelsiusAlreadyTest() {
        Period forcast = new Period();
        forcast.setTemperature(26.7);
        forcast.setTemperatureUnit("C");
		String actual = WeatherUtilities.convertToCelsius(forcast);

        assertEquals("26.7", actual);
    }

}
