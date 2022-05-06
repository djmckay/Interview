package tech.djmckay.demo.service.impl;

import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.djmckay.demo.dto.Weather;
import tech.djmckay.demo.model.Period;
import tech.djmckay.demo.repo.WeatherRepo;
import tech.djmckay.demo.service.WeatherService;
import tech.djmckay.demo.transformer.WeatherTransformer;
import tech.djmckay.demo.transformer.utils.WeatherUtilities;

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
