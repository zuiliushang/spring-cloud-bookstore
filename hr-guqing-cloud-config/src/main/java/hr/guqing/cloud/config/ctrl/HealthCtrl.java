package hr.guqing.cloud.config.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@EnableAutoConfiguration
@Configuration
public class HealthCtrl {

	@Value("${greet:haha}")
	String greet;
	
	@Bean
	public RouterFunction<?> healthFunc(){
		return RouterFunctions.route(RequestPredicates.GET("/greet"), 
				(r)->(ServerResponse.ok().body(BodyInserters.fromObject(greet))));
	}
	
}
