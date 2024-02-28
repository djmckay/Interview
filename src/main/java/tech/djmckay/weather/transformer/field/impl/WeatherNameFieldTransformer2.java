package tech.djmckay.weather.transformer.field.impl;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;

import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.field.WeatherFieldTransformer;

public class WeatherNameFieldTransformer2 implements WeatherFieldTransformer {

	@Override
	public Forecast transform(Forecast day, Period item) {
		day.setName(dayOfWeek(item));
		return day;
	}

	private String dayOfWeek(Period item) {		
		Optional.ofNullable(item.getStartTime()).orElseThrow();
		
		DayOfWeek day = item.getStartTime().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek();
	    return day.getDisplayName(TextStyle.FULL, Locale.US);
	    
	}

}
