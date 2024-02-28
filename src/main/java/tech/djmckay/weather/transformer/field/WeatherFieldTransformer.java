package tech.djmckay.weather.transformer.field;


import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.model.Period;

public interface WeatherFieldTransformer {
	
	Forecast transform(Forecast day, Period item);

}
