package hr.guqing.cloud.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class GuQingOrderApp {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/")
	public Mono<Order> getOrder(@RequestParam(required=true)int orderId){
		Order order = getOrderByOrderId(orderId);
		return Mono.just(order);
	}
	
	@Autowired
	LoadBalancerExchangeFilterFunction lbFunction;
	
	@GetMapping("/test")
	public Mono<Order> getOrderTest(@RequestParam(required=true)int orderId){
		return WebClient.builder().baseUrl("http://hr-guqing-cloud-bookstore")
				.filter(lbFunction)
				.build()
				.get()
				.uri("/?id="+orderId)
				.retrieve()
				.bodyToMono(Order.class);
	}
	
	
	@HystrixCommand(fallbackMethod="defaultGetOrderByOrderId")
	public Order getOrderByOrderId(int orderId) {
		HttpEntity httpEntity = new HttpEntity(null);
		ResponseEntity<Book> bookEntity = restTemplate.exchange("http://hr-guqing-cloud-bookstore?id="+orderId, HttpMethod.GET,httpEntity,Book.class);
		Book book = bookEntity.getBody();
		Order order = new Order();
		order.setId(book.getId());
		order.setInfo(book.getInfo());
		order.setName(book.getName());
		order.setOrderId(orderId);
		order.setOrderName("JAVAORDER");
		return order;
	}
	
	public Order defaultGetOrderByOrderId(int orderId) {
		return new Order();
	}
	
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GuQingOrderApp.class).run(args);
	}
	
}
class Order extends Book{
	
	private int orderId;
	
	private String orderName;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
}
class Book{
	
	private int id;
	
	private String name;

	private String info;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}