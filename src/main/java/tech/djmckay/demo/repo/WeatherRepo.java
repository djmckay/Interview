package tech.djmckay.demo.repo;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.model.Weather;

public interface WeatherRepo {
	
	public Mono<Weather> getDaily();

}
