package tech.djmckay.weather.dto;

import java.util.List;

public class WeatherResponse {

	private List<Forecast> daily;

	public List<Forecast> getDaily() {
		return daily;
	}

	public void setDaily(List<Forecast> daily) {
		this.daily = daily;
	}
	
}
