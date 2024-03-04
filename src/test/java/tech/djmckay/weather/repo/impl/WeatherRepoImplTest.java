package tech.djmckay.weather.repo.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tech.djmckay.weather.model.Period;
import tech.djmckay.weather.model.Properties;
import tech.djmckay.weather.model.Weather;

@ExtendWith(MockitoExtension.class)
class WeatherRepoImplTest {

	@InjectMocks
	private WeatherRepoImpl weatherRepo;
	
	@Mock
	private WebClient weatherClient;
	@Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;
    
	@BeforeEach
	void setUp() throws Exception {
		Weather weatherMock = new Weather();
		Properties properties = new Properties();
		properties.setPeriods(new ArrayList<>());
		Period period = new Period();
		period.setName("Expected Name");
		properties.getPeriods().add(period);
		weatherMock.setProperties(properties);
		when(weatherClient.get())
        .thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri("gridpoints/MLB/33,70/forecast")).thenReturn(requestHeadersMock);
        when(requestHeadersMock.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersMock);
        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
        when(responseMock.bodyToMono(Weather.class)).thenReturn(Mono.just(weatherMock));
	}

	@Test
	void test() {
		Weather response = weatherRepo.getDaily().block();
		assertEquals("Expected Name", response.getProperties().getPeriods().get(0).getName());
	}

}
