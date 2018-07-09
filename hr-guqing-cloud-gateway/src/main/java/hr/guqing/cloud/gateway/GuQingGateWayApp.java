package hr.guqing.cloud.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableZuulProxy
@RestController
@ComponentScan
public class GuQingGateWayApp {
	
	@Value("${greeting}")
	String greet;
	
	@GetMapping(value="forward")
	public Mono<String> greeting(){
		return Mono.just(greet);
	}
	
	/*@Bean
	public RouterFunction<?> routerFunction(){
		return RouterFunctions.route(RequestPredicates.GET("/forward"), 
				(r)->(ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromObject(greet))));
	}
	*/
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GuQingGateWayApp.class).run(args);
	}
	
}
