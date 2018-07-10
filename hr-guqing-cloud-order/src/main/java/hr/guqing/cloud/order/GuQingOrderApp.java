package hr.guqing.cloud.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class GuQingOrderApp {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/")
	public Mono<Order> getOrder(@RequestParam(required=true)int orderId){
		ResponseEntity<Book> bookEntity = restTemplate.getForEntity("http://hr-guqing-cloud-bookstore/", Book.class, ImmutableMap.builder().put("id", orderId).build());
		Book book = bookEntity.getBody();
		Order order = new Order();
		order.setId(book.getId());
		order.setInfo(book.getInfo());
		order.setName(book.getName());
		order.setOrderId(orderId);
		order.setOrderName("JAVAORDER");
		return Mono.just(order);
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