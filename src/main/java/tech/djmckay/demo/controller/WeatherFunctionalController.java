package tech.djmckay.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import tech.djmckay.demo.service.WeatherAsyncService;

@Component
public class WeatherFunctionalController {


	/**
	 * http://localhost:8080/weather/functional/today/
	 * @param ws
	 * @return
	 */
	public RouterFunction<ServerResponse> weather(WeatherAsyncService ws) {
		return route().nest(RequestPredicates.path("/weather/functional"), builder -> {
			builder.GET("/today/all", req -> ok().body(ws.getTodayAll()));
			builder.GET("/today", req -> ok().body(ws.getToday()));
		}).build();

	}
}
