package tech.djmckay.weather.transformer.field.impl;

import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.field.WeatherFieldTransformer;

public class WeatherNameFieldTransformer implements WeatherFieldTransformer {

	@Override
	public Forecast transform(Forecast day, Period item) {
		day.setName(item.getName());
		return day;
	}

	

}
