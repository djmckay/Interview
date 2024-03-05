package tech.djmckay.weather.configs;

import org.eclipse.jetty.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WeatherClientConfig {

	@Value("https://api.weather.gov")
	private String url;
	
	@Bean
	WebClient weatherClient() {
		HttpClient httpClient = new HttpClient();
		JettyClientHttpConnector connector = new JettyClientHttpConnector(httpClient);

		return WebClient.builder().clientConnector(connector).baseUrl(url).build();
	}
}
