package tech.djmckay.weather.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

class WeatherUtilitiesTests {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ", Locale.ENGLISH);

	@Test
    public void convertToCelsiusNullUnitTest() {
        Period forcast = new Period();
        forcast.setTemperature(80);
        forcast.setTemperatureUnit(null);
        double actual = WeatherUtilities.convertToCelsius(forcast);

        assertEquals("26.7", String.valueOf(actual));
    }
	
	@Test
    public void convertToCelsiusInFarenheitUnitTest() {
        Period forcast = new Period();
        forcast.setTemperature(80);
        forcast.setTemperatureUnit("F");
        double actual = WeatherUtilities.convertToCelsius(forcast);
        assertEquals(26.7, actual);
        assertEquals("26.7", String.valueOf(actual));
    }
	
	@Test
    public void convertToCelsiusInCelsiusAlreadyTest() {
        Period forcast = new Period();
        forcast.setTemperature(26.7);
        forcast.setTemperatureUnit("C");
		double actual = WeatherUtilities.convertToCelsius(forcast);
        assertEquals(26.7, actual);
        assertEquals("26.7", String.valueOf(actual));
    }
	
	@Test
    public void convertToCelsius32Test() {
        Period forcast = new Period();
        forcast.setTemperature(32);
        forcast.setTemperatureUnit("F");
        double actual = WeatherUtilities.convertToCelsius(forcast);
        assertEquals(0, actual);
        assertEquals("0.0", String.valueOf(actual));
    }
	
	@Test
    public void convertToCelsiusZeroTest() {
        Period forcast = new Period();
        forcast.setTemperature(0);
        forcast.setTemperatureUnit("F");
        double actual = WeatherUtilities.convertToCelsius(forcast);
        assertEquals(-17.8, actual);
        assertEquals("-17.8", String.valueOf(actual));
    }
	
	@Test
    public void convertToCelsius100Test() {
        Period forcast = new Period();
        forcast.setTemperature(212);
        forcast.setTemperatureUnit("F");
        double actual = WeatherUtilities.convertToCelsius(forcast);
        assertEquals(100, actual);
        assertEquals("100.0", String.valueOf(actual));
    }
	
	@Test
    public void convertToCelsiusEmptyTest() {
        Period forcast = new Period();
        forcast.setTemperatureUnit("F");
        double actual = WeatherUtilities.convertToCelsius(forcast);
        assertEquals(-17.8, actual);
        assertEquals("-17.8", String.valueOf(actual));
    }
	
}
