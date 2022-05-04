package tech.djmckay.demo.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import tech.djmckay.demo.controller.WeatherFunctionalController;
import tech.djmckay.demo.service.WeatherAsyncService;
import tech.djmckay.demo.service.WeatherService;

@Configuration
public class RouterConfigs {

	@Bean
    RouterFunction<ServerResponse> weather(WeatherFunctionalController wfc, WeatherAsyncService ws) {
        return wfc.weather(ws);
    }
}
