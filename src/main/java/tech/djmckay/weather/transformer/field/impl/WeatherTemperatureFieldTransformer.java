package tech.djmckay.weather.transformer.field.impl;

import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.field.WeatherFieldTransformer;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

public class WeatherTemperatureFieldTransformer implements WeatherFieldTransformer {

	@Override
	public Forecast transform(Forecast day, Period item) {
		day.setTempHighInCelsius(WeatherUtilities.convertToCelsius(item));
		return day;
	}


}
