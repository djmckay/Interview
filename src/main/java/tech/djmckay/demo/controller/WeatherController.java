package tech.djmckay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.service.WeatherAsyncService;
import tech.djmckay.demo.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/today")
	public @ResponseBody Weather getToday() {
		final long startTime = System.currentTimeMillis();
		Weather response = weatherService.getToday(); 
		System.out.println(System.currentTimeMillis() - startTime);
		return response;
	}
	
	@GetMapping("/today/all")
	public @ResponseBody Weather getTodayAll() {
		final long startTime = System.currentTimeMillis();
		Weather response = weatherService.getTodayAll(); 
		System.out.println(System.currentTimeMillis() - startTime);
		return response;
	}
	
	
}
