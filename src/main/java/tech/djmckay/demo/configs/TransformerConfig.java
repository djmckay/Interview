package tech.djmckay.demo.configs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tech.djmckay.demo.transformer.field.WeatherFieldTransformer;
import tech.djmckay.demo.transformer.field.impl.WeatherForecastBlurpFieldTransformer;
import tech.djmckay.demo.transformer.field.impl.WeatherNameFieldTransformer;
import tech.djmckay.demo.transformer.field.impl.WeatherNameFieldTransformer2;
import tech.djmckay.demo.transformer.field.impl.WeatherTemperatureFieldTransformer;

@Configuration
public class TransformerConfig {

	@Value("${spring.profiles.active:}")
	private String activeProfile;
	
	@Bean
	public List<WeatherFieldTransformer> weatherFieldTransformers() {
		return Arrays.asList(forecastBlurpTransformer(), nameTransformer(), temperatureTransformer());
	}

	private WeatherFieldTransformer temperatureTransformer() {
		return new WeatherTemperatureFieldTransformer();
	}

	private WeatherFieldTransformer nameTransformer() {
		List<String> profiles = Arrays.asList(activeProfile.split(","));
		return profiles.contains(("nameOption2")) ? 
				new WeatherNameFieldTransformer2() : new WeatherNameFieldTransformer();
	}

	private WeatherFieldTransformer forecastBlurpTransformer() {
		return new WeatherForecastBlurpFieldTransformer();
	}
}
