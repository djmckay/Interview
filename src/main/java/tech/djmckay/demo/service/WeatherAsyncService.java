package tech.djmckay.demo.service;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.dto.Weather;

public interface WeatherAsyncService {

	public Mono<Weather> getToday();

	public Mono<Weather> getTodayAll();

}
