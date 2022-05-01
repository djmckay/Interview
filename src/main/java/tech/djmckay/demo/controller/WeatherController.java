package tech.djmckay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/today")
	public @ResponseBody Weather getToday() {
		return weatherService.getToday(); 
	}
	
	@GetMapping("/today/all")
	public @ResponseBody Weather getTodayAll() {
		return weatherService.getTodayAll(); 
	}
	
	
}
