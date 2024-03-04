package tech.djmckay.weather.repo.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.model.Weather;
import tech.djmckay.weather.repo.WeatherRepo;

@Repository
public class WeatherRepoImpl implements WeatherRepo {

	private WebClient weatherClient;
	
    Logger logger = LoggerFactory.getLogger(WeatherRepoImpl.class);

	@Autowired
	public void setWeatherClient(WebClient weatherClient) {
		this.weatherClient = weatherClient;
	}
	
	@Value("${WEATHER_SERVER_UNITS:}")
	private String units;

	@Override
	public Mono<Weather> getDaily() {
		logger.info("Reactive Repo start");
		String endpoint = Optional.ofNullable(units).isEmpty() ? "gridpoints/MLB/33,70/forecast" : "gridpoints/MLB/33,70/forecast?units="+units;
		
		
		Mono<Weather> result = weatherClient.get() .uri(endpoint)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Weather.class);
		logger.info("Reactive Repo end");

		return result;
	}

}
