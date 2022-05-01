package tech.djmckay.demo.transformer.field.impl;

import tech.djmckay.demo.dto.Forecast;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.transformer.field.WeatherFieldTransformer;

public class WeatherTemperatureFieldTransformer implements WeatherFieldTransformer {

	@Override
	public Forecast transform(Forecast day, Period item) {
		day.setTempHighInCelsius(convertToCelsius(item));
		return day;
	}

	private String convertToCelsius(Period today) {
		if ("C".equalsIgnoreCase(today.getTemperatureUnit())) {
			return today.getTemperature();
		}
		Double f = Double.valueOf(today.getTemperature());
		Double result = (f - 32) * 5/9;
		return String.format("%.1f", result);
	}

}
