package tech.djmckay.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Forecast {
	@JsonProperty("day_name")
	private String name;
	@JsonProperty("temp_high_celsius")
	private String tempHighInCelsius;
	@JsonProperty("forecast_blurp")
	private String forecastBlurp;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTempHighInCelsius() {
		return tempHighInCelsius;
	}
	public void setTempHighInCelsius(String tempHighInCelsius) {
		this.tempHighInCelsius = tempHighInCelsius;
	}
	public String getForecastBlurp() {
		return forecastBlurp;
	}
	public void setForecastBlurp(String forecastBlurp) {
		this.forecastBlurp = forecastBlurp;
	}
	
	

}
