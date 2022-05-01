package tech.djmckay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.service.WeatherAsyncService;

@RestController
@RequestMapping("/weather/async")
public class WeatherReactiveController {

	@Autowired
	private WeatherAsyncService weatherService;
	
	@GetMapping("/today")
	public @ResponseBody Mono<Weather> getToday() {
		final long startTime = System.currentTimeMillis();
		Mono<Weather> response = weatherService.getToday(); 
		System.out.println(System.currentTimeMillis() - startTime);
		return response;
	}
	
	@GetMapping("/today/all")
	public @ResponseBody Mono<Weather> getTodayAll() {
		final long startTime = System.currentTimeMillis();
		Mono<Weather> response =  weatherService.getTodayAll(); 
		System.out.println(System.currentTimeMillis() - startTime);
		return response;
	}
}
