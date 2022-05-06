package tech.djmckay.demo.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.model.Weather;
import tech.djmckay.demo.repo.WeatherRepo;

@Repository
public class WeatherRepoImpl implements WeatherRepo {

	private WebClient weatherClient;
	
	@Autowired
	public void setWeatherClient(WebClient weatherClient) {
		this.weatherClient = weatherClient;
	}
	
	@Value("${WEATHER_SERVER_UNITS:}")
	private String units;

	@Override
	public Mono<Weather> getDaily() {
		System.out.println("Reactive Repo start");
		String endpoint = units.isEmpty() ? "gridpoints/MLB/33,70/forecast" : "gridpoints/MLB/33,70/forecast?units="+units;
		
		
		Mono<Weather> result = weatherClient.get() .uri(endpoint) 
		.accept(MediaType.APPLICATION_JSON)
		.retrieve() 
		.bodyToMono(Weather.class);
		System.out.println("Reactive Repo end");

		return result;
	}

}
