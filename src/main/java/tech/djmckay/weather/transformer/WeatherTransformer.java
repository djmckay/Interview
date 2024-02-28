package tech.djmckay.weather.transformer;

import java.util.function.Predicate;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.model.Period;

public interface WeatherTransformer {

	WeatherResponse transform(tech.djmckay.weather.model.Weather item, Predicate<? super Period> predicate);

	Mono<WeatherResponse> transform(Mono<tech.djmckay.weather.model.Weather> item, Predicate<? super Period> predicate);
}
