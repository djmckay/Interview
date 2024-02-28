package tech.djmckay.weather.service;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.WeatherResponse;

public interface WeatherService {

	public Mono<WeatherResponse> getToday();

	public Mono<WeatherResponse> getTodayAll();

}
