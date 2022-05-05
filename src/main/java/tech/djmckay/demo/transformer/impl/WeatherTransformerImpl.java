package tech.djmckay.demo.transformer.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.djmckay.demo.dto.Forecast;
import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.transformer.WeatherTransformer;
import tech.djmckay.demo.transformer.field.WeatherFieldTransformer;

@Component
public class WeatherTransformerImpl implements WeatherTransformer {
	
	@Autowired
	private List<WeatherFieldTransformer> weatherFieldTransformers;
	
	public Weather transform(tech.djmckay.demo.model.Weather item, Predicate<? super Period> predicate) {
		Weather transformedItem = new Weather();
		Optional.ofNullable(item.getProperties()).orElseThrow();
		Optional.ofNullable(item.getProperties().getPeriods()).orElseThrow();
		transformedItem.setDaily(item.getProperties().getPeriods().stream()
				.filter(predicate)
				.map(today -> {
			Forecast daily = new Forecast();
			weatherFieldTransformers.stream().forEach(transformer -> {
				transformer.transform(daily, today);
			});
			return daily;
		}).collect(Collectors.toList()));
		return transformedItem;
	}

	
	
}
