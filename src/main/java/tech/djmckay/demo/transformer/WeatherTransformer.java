package tech.djmckay.demo.transformer;

import java.util.function.Predicate;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.model.Period;

public interface WeatherTransformer {

	Weather transform(tech.djmckay.demo.model.Weather item, Predicate<? super Period> predicate);

	Mono<Weather> transform(Mono<tech.djmckay.demo.model.Weather> item, Predicate<? super Period> predicate);
}
