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
public class WeatherAsyncController {

	@Autowired
	private WeatherAsyncService weatherService;
	
	@GetMapping("/today")
	public @ResponseBody Mono<Weather> getToday() {
		return weatherService.getToday(); 
	}
	
	@GetMapping("/today/all")
	public @ResponseBody Mono<Weather> getTodayAll() {
		return weatherService.getTodayAll(); 
	}
}
