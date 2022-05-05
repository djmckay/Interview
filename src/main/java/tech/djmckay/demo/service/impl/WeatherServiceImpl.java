package tech.djmckay.demo.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;
import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.repo.WeatherRepo;
import tech.djmckay.demo.service.WeatherService;
import tech.djmckay.demo.transformer.WeatherTransformer;
import tech.djmckay.demo.transformer.utils.WeatherUtilities;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepo weatherRepo;
	
	@Autowired
	private WeatherTransformer weatherTransformer;
	

	@Override
	public Weather getToday() {
		return this.getToday(WeatherUtilities.latest);
	}

	@Override
	public Weather getTodayAll() {
		return this.getToday(WeatherUtilities.todayAllPeriods);
	}
	
	private Weather getToday(Predicate<? super Period> predicate) {
		System.out.println("Blocking Service start");
		Weather weather = weatherRepo.getDaily()
				.doOnError(e -> {
			e.printStackTrace();
			throw new RuntimeException("Error Retrieving Weather");
		})
				.map(item -> {
			return weatherTransformer.transform(item, predicate);
		}).block();
		System.out.println("Blocking Service End");
		return weather;
	}

}
