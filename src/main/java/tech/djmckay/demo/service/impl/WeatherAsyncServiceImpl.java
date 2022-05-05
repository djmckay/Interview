package tech.djmckay.demo.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.repo.WeatherRepo;
import tech.djmckay.demo.service.WeatherAsyncService;
import tech.djmckay.demo.transformer.WeatherTransformer;
import tech.djmckay.demo.transformer.utils.WeatherUtilities;

@Service
public class WeatherAsyncServiceImpl implements WeatherAsyncService {

	@Autowired
	private WeatherRepo weatherRepo;
	
	@Autowired
	private WeatherTransformer weatherTransformer;

	@Override
	public Mono<Weather> getToday() {
		return this.getToday(WeatherUtilities.latest);
	}

	@Override
	public Mono<Weather> getTodayAll() {
		return this.getToday(WeatherUtilities.todayAllPeriods);
	}
	
	private Mono<Weather> getToday(Predicate<? super Period> predicate) {
		System.out.println("Reactive Service start");
		Mono<Weather> weather = weatherRepo.getDaily()
				.doOnError(e -> {
			e.printStackTrace();
			throw new RuntimeException("Error Retrieving Weather");
		}).map(item -> {
			System.out.println("Reactive Map Start");
			return weatherTransformer.transform(item, predicate);
		});
		System.out.println("Reactive Service end");
		return weather;
	}
	

}
