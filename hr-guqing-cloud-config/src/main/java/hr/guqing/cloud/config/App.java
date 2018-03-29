package hr.guqing.cloud.config;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class App {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(App.class).web(WebApplicationType.REACTIVE).run(args);
	}
	
}
