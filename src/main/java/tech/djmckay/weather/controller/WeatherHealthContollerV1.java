package tech.djmckay.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class WeatherHealthContollerV1 {
	
	@GetMapping("/check")
	public @ResponseBody Boolean getHealth() throws Exception {
		
		return Boolean.TRUE;
	}

}
