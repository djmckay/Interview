package tech.djmckay.demo.service;

import tech.djmckay.demo.dto.Weather;

public interface WeatherService {

	public Weather getToday();

	public Weather getTodayAll();
}
