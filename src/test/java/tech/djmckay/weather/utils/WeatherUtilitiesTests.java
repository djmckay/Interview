package tech.djmckay.weather.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

class WeatherUtilitiesTests {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

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
	
	@Test
    public void convertDayOfWeekThursdayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-02-29"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Thursday", actual);
    }
	
	@Test
    public void convertDayOfWeekFridayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-03-01"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Friday", actual);
    }
	
	@Test
    public void convertDayOfWeekSaturdayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-03-02"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Saturday", actual);
    }

	@Test
    public void convertDayOfWeekSundayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-05-05"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Sunday", actual);
    }
	
	@Test
    public void convertDayOfWeekMondayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-05-06"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Monday", actual);
    }
	
	@Test
    public void convertDayOfWeekTuedayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-05-07"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Tuesday", actual);
    }
	
	@Test
    public void convertDayOfWeekWednesdayTest() throws ParseException {
        Period forcast = new Period();
        forcast.setStartTime(formatter.parse("2024-05-08"));
		String actual = WeatherUtilities.dayOfWeek(forcast);

        assertEquals("Wednesday", actual);
    }
}
