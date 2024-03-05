package tech.djmckay.weather.transformer;

import java.util.function.Predicate;

import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.model.Period;

public interface WeatherTransformer {

	WeatherResponse transform(tech.djmckay.weather.model.Weather item, Predicate<? super Period> predicate);
}
