package com.neusoft.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
*Create at 2018年8月22日 下午3:06:37
*
*@autuor huyz
*
*@version 1.0
*
*ProjectName WebFlux
*
*Description: 
*        
*/


@RestController
public class FluxController {

	
	
	@GetMapping("/hello")
	public Mono<String> hello() {
		return Mono.just("hello  web flux");
	}
}
