package tech.djmckay.weather.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import tech.djmckay.weather.dto.ErrorMessage;

@ControllerAdvice
public class WeatherControllerAdvice {

	@ExceptionHandler(value = {Exception.class})
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        LocalDate.now(),
	        ex.getMessage());
	    
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	  }
}
