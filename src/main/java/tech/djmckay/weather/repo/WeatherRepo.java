package tech.djmckay.weather.repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.djmckay.weather.model.Weather;

public interface WeatherRepo {
	
	public Mono<Weather> getDaily();

}
