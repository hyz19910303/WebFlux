package com.neusoft.webflux;

import java.time.Duration;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WebFluxApplicationTests {

	@Test
	public void contextLoads() {
		Flux<String> take = Flux.interval(Duration.ofSeconds(1)).map(p -> {
			return p + "	message";
		}).take(5);
		try {
			Mono<String> bodyToMono = WebClient.create("http://localhost:9690").post().uri("/stream")
					.contentType(MediaType.APPLICATION_STREAM_JSON).body(Flux.interval(Duration.ofSeconds(1)).map(p -> {

						return p + "";
					}).take(5), String.class).retrieve().bodyToMono(String.class);
			System.out.println(bodyToMono.block());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
