package tech.djmckay.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WeatherClientConfig {

	@Value("${WEATHER_SERVER_URL}")
	private String url;
	
	@Bean
	WebClient weatherClient() {
		return WebClient.create(url);
	}
}
