package hr.guqing.cloud.bookstore;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GuQingBookStoreApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GuQingBookStoreApp.class).web(WebApplicationType.REACTIVE).run(args);
	}
	
}
