package tech.djmckay.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	Logger logger = LoggerFactory.getLogger(WeatherReactiveControllerV1.class);
	
	@Autowired
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@GetMapping("/today")
	public @ResponseBody Mono<WeatherResponse> getToday() {
		logger.info("Reactive Controller start");
		final long startTime = System.currentTimeMillis();
		Mono<WeatherResponse> response = weatherService.getToday(); 
		logger.info(String.valueOf(System.currentTimeMillis() - startTime));
		logger.info("Reactive Controller end");
		return response;
	}
	
//	@GetMapping("/today/all")
//	public @ResponseBody Mono<WeatherResponse> getTodayAll() {
//		System.out.println("Reactive Controller start");
//		final long startTime = System.currentTimeMillis();
//		Mono<WeatherResponse> response =  weatherService.getTodayAll(); 
//		System.out.println(System.currentTimeMillis() - startTime);
//		System.out.println("Reactive Controller end");
//
//		return response;
//	}
}
