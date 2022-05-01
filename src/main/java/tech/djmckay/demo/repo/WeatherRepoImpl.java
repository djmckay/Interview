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
	
	@Value("${WEATHER_SERVER_UNITS:}")
	private String units;

	@Override
	public Mono<Weather> getDaily() {
		String endpoint = units.isEmpty() ? "gridpoints/MLB/33,70/forecast" : "gridpoints/MLB/33,70/forecast?units="+units;
		
		WebClient client = WebClient.create(url);
		Mono<Weather> result = client.get() .uri(endpoint) 
		.accept(MediaType.APPLICATION_JSON) 
		.retrieve() // Send request
		.bodyToMono(Weather.class);
		return result;
	}

}
