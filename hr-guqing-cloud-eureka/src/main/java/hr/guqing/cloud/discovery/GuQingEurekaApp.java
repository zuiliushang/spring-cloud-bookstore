package hr.guqing.cloud.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GuQingEurekaApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GuQingEurekaApp.class).run(args);
	}
	
}
