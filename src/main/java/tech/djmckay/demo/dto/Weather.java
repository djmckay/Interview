package tech.djmckay.demo.dto;

import java.util.List;

public class Weather {

	private List<Forecast> daily;

	public List<Forecast> getDaily() {
		return daily;
	}

	public void setDaily(List<Forecast> daily) {
		this.daily = daily;
	}
	
}
