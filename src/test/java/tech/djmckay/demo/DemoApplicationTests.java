//package tech.djmckay.demo;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import org.springframework.web.reactive.function.client.WebClient;
//
//
//import reactor.core.publisher.Mono;
//import tech.djmckay.weather.DemoApplication;
//import tech.djmckay.weather.dto.WeatherResponse;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
//class DemoApplicationTests {
//
//	public static final String SERVER_URL = "http://localhost:";
//
//	
//	@LocalServerPort
//    private int port;
//	
//	@Test
//	void contextLoads() {
//	}
//	
//	@Test
//    public void testWebTestClientWithController() {
//		System.out.println("*** TEST " + "testWebTestClientWithController" + " ***");
//
//        WebTestClient.bindToServer()
//        	.baseUrl("http://localhost:" + port)
//            .build()
//            .get()
//            .uri("/weather/today")
//            .exchange()
//            .expectStatus()
//            .isOk()
//            .expectBody()
//            .jsonPath("daily")
//            .exists();
//       }
//	
//	@Test
//    public void testWebTestClientWithAsyncController() {
//		System.out.println("*** TEST " + "testWebTestClientWithAsyncController" + " ***");
//
//        WebTestClient.bindToServer()
//        	.baseUrl("http://localhost:" + port)
//            .build()
//            .get()
//            .uri("/weather/async/today")
//            .exchange()
//            .expectStatus()
//            .isOk()
//            .expectBody()
//            .jsonPath("daily")
//            .exists();
//       } 
//	
//	@Test
//	public void testWebclient() {
//
//		System.out.println("*** TEST " + "testWebclient" + " ***");
//
//		WebClient client = WebClient.create(SERVER_URL+port);
//		Mono<WeatherResponse> result = client.get() //
//				.uri("/weather/today") //
//				.accept(MediaType.APPLICATION_JSON) //
//				.retrieve() //
//				.bodyToMono(WeatherResponse.class);
//
//		// Wait for account to be returned
//		WeatherResponse weather = result.block();
//
//		System.out.println("Weather: " + weather.getDaily().get(0).getForecastBlurp());
//
//		// Same again, but this time, process asynchronously using subscribe
//		final long startTime = System.currentTimeMillis();
//		Mono<WeatherResponse> result2 = client.get() //
//				.uri("/weather/today") //
//				.accept(MediaType.APPLICATION_JSON) //
//				.retrieve() //
//				.bodyToMono(WeatherResponse.class);
//
//		result2.subscribe(a -> {
//			System.out.println(System.currentTimeMillis() - startTime);
//			System.out.println("Weather: " + a.getDaily().get(0).getForecastBlurp());
//		});
//	}
//	
//	@Test
//	public void testWebclientBlock() {
//
//		System.out.println("*** TEST " + "testWebclientBlock" + " ***");
//
//
//		WebClient client = WebClient.create(SERVER_URL+port);
//		final long startTime = System.currentTimeMillis();
//
//		Mono<WeatherResponse> result = client.get() //
//				.uri("/weather/today") //
//				.accept(MediaType.APPLICATION_JSON) //
//				.retrieve() //
//				.bodyToMono(WeatherResponse.class);
//
//		// Wait for account to be returned
//
//		WeatherResponse weather = result.block();
//		System.out.println(System.currentTimeMillis() - startTime);
//		System.out.println("Weather: " + weather.getDaily().get(0).getForecastBlurp());
//
//	}
//	
//	@Test
//	public void testAsyncWebclient() {
//
//		System.out.println("*** TEST " + "testAsyncWebclient" + " ***");
//
//		WebClient client = WebClient.create(SERVER_URL+port);
//		Mono<WeatherResponse> result = client.get() //
//				.uri("/weather/async/today") //
//				.accept(MediaType.APPLICATION_JSON) //
//				.retrieve() //
//				.bodyToMono(WeatherResponse.class);
//
//		// Wait for account to be returned
//		WeatherResponse weather = result.block();
//
//		System.out.println("Weather: " + weather.getDaily().get(0).getForecastBlurp());
//
//		// Same again, but this time, process asynchronously using subscribe
//		final long startTime = System.currentTimeMillis();
//		Mono<WeatherResponse> result2 = client.get() //
//				.uri("/weather/today") //
//				.accept(MediaType.APPLICATION_JSON) //
//				.retrieve() //
//				.bodyToMono(WeatherResponse.class);
//
//		result2.subscribe(a -> {
//			System.out.println(System.currentTimeMillis() - startTime);
//			System.out.println("Weather: " + a.getDaily().get(0).getForecastBlurp());
//		});
//	}
//	
//	@Test
//	public void testAsyncWebclientBlock() {
//
//		System.out.println("*** TEST " + "testAsyncWebclientBlock" + " ***");
//
//
//		WebClient client = WebClient.create(SERVER_URL+port);
//		final long startTime = System.currentTimeMillis();
//
//		Mono<WeatherResponse> result = client.get() //
//				.uri("/weather/async/today") //
//				.accept(MediaType.APPLICATION_JSON) //
//				.retrieve() //
//				.bodyToMono(WeatherResponse.class);
//
//		// Wait for account to be returned
//
//		WeatherResponse weather = result.block();
//
//		System.out.println("Weather: " + weather.getDaily().get(0).getForecastBlurp());
//		System.out.println(System.currentTimeMillis() - startTime);
//
//		
//	}
//
//}
