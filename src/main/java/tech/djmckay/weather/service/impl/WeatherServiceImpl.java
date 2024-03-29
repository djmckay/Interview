package tech.djmckay.weather.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.dto.Forecast;
import tech.djmckay.weather.dto.WeatherResponse;
import tech.djmckay.weather.exception.WeatherNotFoundException;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.repo.WeatherRepo;
import tech.djmckay.weather.service.WeatherService;
import tech.djmckay.weather.transformer.WeatherTransformer;
import tech.djmckay.weather.transformer.utils.WeatherUtilities;

@Service
public class WeatherServiceImpl implements WeatherService {

	private WeatherRepo weatherRepo;
	private WeatherTransformer weatherTransformer;
	
	Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

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
		return this.getToday(WeatherUtilities.todayAllPeriods);
	}
	
	private Mono<WeatherResponse> getToday(Predicate<? super Period> predicate) {
		logger.info("Reactive Service start");
		Mono<WeatherResponse> weather = weatherRepo.getDaily().doOnError(e -> {
			logger.info(e.getLocalizedMessage());
			throw new WeatherNotFoundException(e.getLocalizedMessage());
		}).flatMap(item -> {
				logger.info("Reactive Transformer flatMap start");
				//TODO VALIDATE?
				return Mono.just(weatherTransformer.transform(item, predicate));
		});

		logger.info("Reactive Service end");
		return weather;
	}
    
    
	

}
