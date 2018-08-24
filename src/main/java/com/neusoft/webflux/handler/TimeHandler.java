package com.neusoft.webflux.handler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Create at 2018年8月22日 下午4:08:02
 *
 * @autuor huyz
 *
 * @version 1.0
 *
 *          ProjectName WebFlux
 *
 *          Description:  处理器 相当于spring5以前的web 的controller
 * 
 */

@Component // 等于原来的 controller 里面具体执行的方法 但是未和requestmapping  绑定
public class TimeHandler {

	// 获取时间处理
	public Mono<ServerResponse> date(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
				.body(Mono.just("today time is "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ss SS"))),
						String.class);
	}

	// 获取时间处理
	public Mono<ServerResponse> time(ServerRequest request) {
		Optional<String> name = request.queryParam("name");
		if (!name.isPresent()) {
			return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN)
					.body(Mono.just("miss  param name"), String.class);
		}
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just(
				name.get() + "today date is " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
				String.class);
	}
	
	
	// 流处理
	/*
	 * SSE：服务端推送（Server Send Event），在客户端发起一次请求后会保持该连接，服务器端基于该连接持续向客户端发送数据，从HTML5开始加入。
	 * 利用interval生成每秒一个数据的流
	 */
	public Mono<ServerResponse> stream(ServerRequest request) {
			// 类型设置为  MediaType.TEXT_EVENT_STREAM
			return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).
					body(Flux.interval(Duration.ofSeconds(1)).map(p->LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ss SS"))),
					String.class);
		}
	
	
	

}
