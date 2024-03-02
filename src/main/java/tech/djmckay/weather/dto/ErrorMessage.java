package tech.djmckay.weather.dto;

import java.time.LocalDate;

public class ErrorMessage {

	private String message;
	private LocalDate date;

	public ErrorMessage(LocalDate now, String message) {
		this.date = now;
		this.setMessage(message);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
