package hr.guqing.cloud.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
@EnableZuulProxy
public class GuQingGateWayApp {
	
	@Value("${greeting}")
	String greet;
	
	@Bean
	public RouterFunction<?> routerFunction(){
		return RouterFunctions.route(RequestPredicates.GET("/greet"), 
				(r)->(ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromObject(greet))));
	}
	
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GuQingGateWayApp.class).web(WebApplicationType.REACTIVE).run(args);
	}
	
}
