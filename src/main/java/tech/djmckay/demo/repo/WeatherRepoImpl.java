package tech.djmckay.demo.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.model.Weather;

@Repository
public class WeatherRepoImpl implements WeatherRepo {

	@Value("${WEATHER_SERVER_URL}")
	private String url;

	@Override
	public Mono<Weather> getDaily() {
		WebClient client = WebClient.create(url);
		Mono<Weather> result = client.get() .uri("gridpoints/MLB/33,70/forecast") 
		.accept(MediaType.APPLICATION_JSON) 
		.retrieve() // Send request
		.bodyToMono(Weather.class);
		return result;
	}

}
