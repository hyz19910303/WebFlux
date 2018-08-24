package com.neusoft.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.neusoft.webflux.handler.TimeHandler;

/**
*Create at 2018年8月22日 下午3:14:15
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

@Configuration
public class RouteConfig {
	
	
	
//	@Bean
//	public RouterFunction<ServerResponse> route(){
//		
//		return RouterFunctions.route(RequestPredicates.GET("/time"), (ServerRequest request)->{
//			return ServerResponse.ok().
//					contentType(MediaType.TEXT_PLAIN).
//					body(Mono.just("today time is "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ss SS"))),String.class);
//		}).andRoute(RequestPredicates.GET("/date"), (request)->{
//			Optional<String> name = request.queryParam("name");
//			if(!name.isPresent()) {
//				return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN)
//						.body(Mono.just("miss  param name"),String.class);
//			}
//			return ServerResponse.ok().
//					contentType(MediaType.TEXT_PLAIN).
//					body(Mono.just(name.get()+"today date is "+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),String.class);
//		});
////			return ServerResponse.ok().body(Mono.just(""),String.class).;
//	}
	
	@Autowired
	private TimeHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> route(){
		return RouterFunctions.route(RequestPredicates.GET("/time"), handler::time)
				.andRoute(RequestPredicates.GET("/date"), handler::date)
				.andRoute(RequestPredicates.POST("/stream"), handler::stream);
	}
	
	
}
