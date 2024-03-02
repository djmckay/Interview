package tech.djmckay.weather.exception;

public class WeatherException extends RuntimeException {

	public WeatherException(String message, String details) {
		super();
		this.message = message;
		this.setDetails(details);
	}

	private static final long serialVersionUID = 2246415120444550684L;

	private String message;
	private String details;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	
}
