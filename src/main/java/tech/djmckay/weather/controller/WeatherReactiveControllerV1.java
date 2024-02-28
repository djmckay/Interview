package tech.djmckay.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.service.WeatherService;

@RestController
@RequestMapping("/v1/weather")
public class WeatherReactiveControllerV1 {

	private WeatherService weatherService;
	
	@Autowired
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@GetMapping("/today")
	public @ResponseBody Mono<WeatherResponse> getToday() {
		System.out.println("Reactive Controller start");
		final long startTime = System.currentTimeMillis();
		Mono<WeatherResponse> response = weatherService.getToday(); 
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println("Reactive Controller end");
		return response;
	}
	
	@GetMapping("/today/all")
	public @ResponseBody Mono<WeatherResponse> getTodayAll() {
		System.out.println("Reactive Controller start");
		final long startTime = System.currentTimeMillis();
		Mono<WeatherResponse> response =  weatherService.getTodayAll(); 
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println("Reactive Controller end");

		return response;
	}
}
