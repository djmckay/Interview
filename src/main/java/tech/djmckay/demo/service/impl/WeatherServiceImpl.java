package tech.djmckay.demo.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.repo.WeatherRepo;
import tech.djmckay.demo.service.WeatherService;
import tech.djmckay.demo.transformer.WeatherTransformer;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepo weatherRepo;
	
	@Autowired
	private WeatherTransformer weatherTransformer;

	private Predicate<? super Period> latest = period -> {
		return period.getNumber().equals("1");
	};
	
	private Predicate<? super Period> todayAllPeriods = period -> {
		LocalDate.now().atStartOfDay();
		LocalDate.now().datesUntil(period.getStartTime().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate());
		return LocalDate.now().datesUntil(period.getStartTime().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate()).count() == 0;
	};
	

	@Override
	public Weather getToday() {
		return this.getToday(latest);
	}

	@Override
	public Weather getTodayAll() {
		return this.getToday(todayAllPeriods);
	}
	
	private Weather getToday(Predicate<? super Period> predicate) {
		Weather weather = weatherRepo.getDaily()
				.doOnError(e -> {
			e.printStackTrace();
			throw new RuntimeException("Error Retrieving Weather");
		}).map(item -> {
			return weatherTransformer.transform(item, predicate);
		}).block();
		return weather;
	}
	

}
