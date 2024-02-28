package tech.djmckay.weather.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.model.Properties;
import tech.djmckay.weather.model.Weather;
import tech.djmckay.weather.repo.WeatherRepo;
import tech.djmckay.weather.transformer.impl.WeatherTransformerImpl;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

	@Mock
	private WeatherRepo weatherRepo;
	
	@InjectMocks
	private WeatherServiceImpl weatherService;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	
	@BeforeEach
	void setUp() throws Exception {
		
        
        weatherService.setWeatherTransformer(new WeatherTransformerImpl());

	}

	@Test
	void NoSuchElementExceptionNameFieldTest() {
		
		Weather basicWeather = new Weather();
		Mono.just(basicWeather);
		Properties properties = new Properties();
		List<Period> periods = new ArrayList<>();
		Period thisMorning = new Period();
		thisMorning.setName(null);
		thisMorning.setTemperature(80);
		periods.add(thisMorning);
		Period thisAfternoon = new Period();
		thisAfternoon.setName(null);
		thisAfternoon.setTemperature(90);
		periods.add(thisAfternoon);
		Period tonight = new Period();
		tonight.setName(null);
		tonight.setTemperature(70);
		periods.add(tonight);
		Period tomorrow = new Period();
		tonight.setName(null);
		tonight.setTemperature(60);
		periods.add(tomorrow);
		properties.setPeriods(periods);
		basicWeather.setProperties(properties);
        when(weatherRepo.getDaily()).thenReturn(Mono.just(basicWeather));
        
		assertThrows(NoSuchElementException.class, () -> {
		WeatherResponse response = weatherService.getToday().block();
		}
		);
//		assertEquals(response.getDaily().get(0).getTempHighInCelsius(), "27.2");
	}
	
	@Test
	void NoSuchElementExceptionStartTimeFieldTest() throws ParseException {
		
		Weather basicWeather = new Weather();
		Mono.just(basicWeather);
		Properties properties = new Properties();
		List<Period> periods = new ArrayList<>();
		Period thisMorning = new Period();
		thisMorning.setName("Today");
		thisMorning.setTemperature(80);
		periods.add(thisMorning);
		Period thisAfternoon = new Period();
		thisAfternoon.setName("This Afternoon");
		thisAfternoon.setTemperature(90);

		periods.add(thisAfternoon);
		Period tonight = new Period();
		tonight.setName("Tonight");
		tonight.setTemperature(70);
		tonight.setStartTime(formatter.parse("2024-02-28"));
		periods.add(tonight);
		Period tomorrow = new Period();
		tomorrow.setName("Tomorrow");
		tomorrow.setTemperature(60);
		tomorrow.setStartTime(formatter.parse("2024-02-29"));
		periods.add(tomorrow);
		properties.setPeriods(periods);
		basicWeather.setProperties(properties);
        when(weatherRepo.getDaily()).thenReturn(Mono.just(basicWeather));
        
		assertThrows(NoSuchElementException.class, () -> {
		WeatherResponse response = weatherService.getToday().block();
		}
		);
//		assertEquals(response.getDaily().get(0).getTempHighInCelsius(), "27.2");
	}
	
	@Test
	void getTodaysHighWithMultipleCurrentTest() throws ParseException {
		
		Weather basicWeather = new Weather();
		Mono.just(basicWeather);
		Properties properties = new Properties();
		List<Period> periods = new ArrayList<>();
		Period thisMorning = new Period();
		thisMorning.setName("Today");
		thisMorning.setTemperature(80);
		thisMorning.setStartTime(formatter.parse("2024-02-28"));
		periods.add(thisMorning);
		Period thisAfternoon = new Period();
		thisAfternoon.setName("This Afternoon");
		thisAfternoon.setTemperature(90);

		thisAfternoon.setStartTime(formatter.parse("2024-02-28"));
		periods.add(thisAfternoon);
		Period tonight = new Period();
		tonight.setName("Tonight");
		tonight.setTemperature(70);
		tonight.setStartTime(formatter.parse("2024-02-28"));
		periods.add(tonight);
		Period tomorrow = new Period();
		tomorrow.setName("Tomorrow");
		tomorrow.setTemperature(60);
		tomorrow.setStartTime(formatter.parse("2024-02-29"));
		periods.add(tomorrow);
		properties.setPeriods(periods);
		basicWeather.setProperties(properties);
        when(weatherRepo.getDaily()).thenReturn(Mono.just(basicWeather));
        
		WeatherResponse response = weatherService.getToday().block();
		assertEquals(response.getDaily().get(0).getTempHighInCelsius(), "32.2");
	}

}
