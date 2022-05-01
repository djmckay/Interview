package tech.djmckay.demo.transformer.field.impl;

import tech.djmckay.demo.dto.Forecast;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.transformer.field.WeatherFieldTransformer;

public class WeatherForecastBlurpFieldTransformer implements WeatherFieldTransformer {

	@Override
	public Forecast transform(Forecast day, Period item) {
		day.setForecastBlurp(item.getShortForecast());
		return day;
	}

}
