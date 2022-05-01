package tech.djmckay.demo.transformer.field;


import tech.djmckay.demo.dto.Forecast;
import tech.djmckay.demo.model.Period;

public interface WeatherFieldTransformer {
	
	Forecast transform(Forecast day, Period item);

}
