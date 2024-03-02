package tech.djmckay.weather.exception;

public class WeatherNotFoundException extends WeatherException {

	private static final long serialVersionUID = 1L;
		
	public WeatherNotFoundException(String e) {
		super("Error Retrieving Weather", e);
	}

}
