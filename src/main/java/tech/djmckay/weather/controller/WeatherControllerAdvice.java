package tech.djmckay.weather.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import tech.djmckay.weather.dto.ErrorMessage;
import tech.djmckay.weather.exception.WeatherNotFoundException;

@ControllerAdvice
public class WeatherControllerAdvice {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {Exception.class})
	@ResponseBody
	  public ErrorMessage generalException(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        LocalDate.now(),
	        ex.getMessage());
	    return message;
	  }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {WeatherNotFoundException.class})
	@ResponseBody
	  public ErrorMessage resourceNotFoundException(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        LocalDate.now(),
	        ex.getMessage());
	    return message;
	  }
}
