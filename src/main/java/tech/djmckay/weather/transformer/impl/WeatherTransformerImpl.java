package tech.djmckay.weather.transformer.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.WeatherTransformer;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

@Component
public class WeatherTransformerImpl implements WeatherTransformer {
	
	Logger logger = LoggerFactory.getLogger(WeatherTransformerImpl.class);

	@Override
	public Mono<WeatherResponse> transform(Mono<tech.djmckay.weather.model.Weather> item, Predicate<? super Period> predicate) {
		logger.info("Reactive Transformer start");
		return item.map(weather -> {
			//TODO VALIDATE?
			WeatherResponse transformedItem = new WeatherResponse();
			 
			Forecast results = weather.getProperties().getPeriods().stream()
					.filter(predicate)
					.max(Comparator.comparing(Period::getTemperature))
					.map(today -> {
						Forecast daily = new Forecast();
						daily.setForecastBlurp(today.getShortForecast());
						Optional.ofNullable(today.getStartTime()).orElseThrow();
						String dayName = today.getStartTime().getDayOfWeek().name();
						daily.setName(dayName.substring(0, 1) + dayName.substring(1).toLowerCase());
						daily.setTempHighInCelsius(WeatherUtilities.convertToCelsius(today));


						return daily;
					})
					.orElseThrow(NoSuchElementException::new);
			transformedItem.setDaily(Arrays.asList(results));
			logger.info("Reactive Transformer end");
			return transformedItem;
		});
		
	}


}
