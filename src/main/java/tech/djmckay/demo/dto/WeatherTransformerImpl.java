package tech.djmckay.demo.dto;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.transformer.WeatherTransformer;

@Component
public class WeatherTransformerImpl implements WeatherTransformer {
	
	public Weather transform(tech.djmckay.demo.model.Weather item, Predicate<? super Period> predicate) {
		Weather transformedItem = new Weather();
		Optional.ofNullable(item.getProperties()).orElseThrow();
		Optional.ofNullable(item.getProperties().getPeriods()).orElseThrow();
		transformedItem.setDaily(item.getProperties().getPeriods().stream().filter(predicate)
		.map(today -> {
			Forecast daily = new Forecast();
			daily.setName(today.getName());
			daily.setForecastBlurp(today.getShortForecast());
			daily.setTempHighInCelsius(convertToCelsius(today));
			return daily;
		}).collect(Collectors.toList()));
		return transformedItem;
	}

	private String convertToCelsius(Period today) {
		if ("C".equalsIgnoreCase(today.getTemperatureUnit())) {
			return today.getTemperature();
		}
		Double f = Double.valueOf(today.getTemperature());
		Double result = (f - 32) * 5/9;
		return String.format("%.1f", result);
	}
	
}
