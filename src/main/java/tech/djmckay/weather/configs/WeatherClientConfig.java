package tech.djmckay.weather.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WeatherClientConfig {

	@Value("https://api.weather.gov")
	private String url;
	
	@Bean
	WebClient weatherClient() {
		return WebClient.create(url);
	}
}
