package tech.djmckay.weather.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.repo.WeatherRepo;
import tech.djmckay.weather.service.WeatherService;
import tech.djmckay.weather.transformer.WeatherTransformer;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

@Service
public class WeatherServiceImpl implements WeatherService {

	private WeatherRepo weatherRepo;
	private WeatherTransformer weatherTransformer;

	@Autowired
	public void setWeatherRepo(WeatherRepo weatherRepo) {
		this.weatherRepo = weatherRepo;
	}

	@Autowired
	public void setWeatherTransformer(WeatherTransformer weatherTransformer) {
		this.weatherTransformer = weatherTransformer;
	}
	
	@Override
	public Mono<WeatherResponse> getToday() {
		return this.getToday(WeatherUtilities.current);
	}

	@Override
	public Mono<WeatherResponse> getTodayAll() {
		return this.getToday(WeatherUtilities.todayAllPeriods);
	}
	
	private Mono<WeatherResponse> getToday(Predicate<? super Period> predicate) {
		System.out.println("Reactive Service start");
		Mono<WeatherResponse> weather = weatherRepo.getDaily()
				.doOnError(e -> {
			e.printStackTrace();
			throw new RuntimeException("Error Retrieving Weather");
		}).map(item -> {
			System.out.println("Reactive Map Start");
			return weatherTransformer.transform(item, predicate);
		});
		//Mono<Weather> weather = weatherTransformer.transform(weatherRepo.getDaily(), predicate);
		System.out.println("Reactive Service end");
		return weather;
	}
    
    
	

}
