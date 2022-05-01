package tech.djmckay.demo.transformer.field.impl;

import tech.djmckay.demo.dto.Forecast;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.transformer.field.WeatherFieldTransformer;

public class WeatherNameFieldTransformer implements WeatherFieldTransformer {

	@Override
	public Forecast transform(Forecast day, Period item) {
		day.setName(item.getName());
		return day;
	}

	

}
