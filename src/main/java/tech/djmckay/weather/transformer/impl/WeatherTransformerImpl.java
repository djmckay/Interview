package tech.djmckay.weather.transformer.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.transformer.WeatherTransformer;
import tech.djmckay.weather.transformer.field.WeatherFieldTransformer;

@Component
public class WeatherTransformerImpl implements WeatherTransformer {
	
	@Autowired
	private List<WeatherFieldTransformer> weatherFieldTransformers;
	
	public WeatherResponse transform(tech.djmckay.weather.model.Weather item, Predicate<? super Period> predicate) {
		WeatherResponse transformedItem = new WeatherResponse();
		Optional.ofNullable(item.getProperties()).orElseThrow();
		Optional.ofNullable(item.getProperties().getPeriods()).orElseThrow();
		transformedItem.setDaily(item.getProperties().getPeriods().stream()
				.filter(predicate)
				.map(today -> {
			Forecast daily = new Forecast();
			weatherFieldTransformers.forEach(transformer ->
				transformer.transform(daily, today)
			);
			return daily;
		}).collect(Collectors.toList()));
		return transformedItem;
	}

	@Override
	public Mono<WeatherResponse> transform(Mono<tech.djmckay.weather.model.Weather> item, Predicate<? super Period> predicate) {
		System.out.println("Reactive Transformer start");
		return item.map(weather -> {
			WeatherResponse transformedItem = new WeatherResponse();
			List<Forecast> results = weather.getProperties().getPeriods().stream()
					.filter(predicate)
					.map(today -> {
						Forecast daily = new Forecast();
						weatherFieldTransformers.forEach(transformer ->
							transformer.transform(daily, today)
						);
						return daily;
					}).collect(Collectors.toList());
			transformedItem.setDaily(results);
			System.out.println("Reactive Transformer end");
			return transformedItem;
		});
		
	}


}
