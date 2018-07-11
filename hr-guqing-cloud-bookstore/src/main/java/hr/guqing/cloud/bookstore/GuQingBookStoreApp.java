package hr.guqing.cloud.bookstore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GuQingBookStoreApp {

	@Value("${greeting}")
	String greet;
	
	/*@Bean
	public RouterFunction<?> routerFunction(){
		return RouterFunctions.route(RequestPredicates.GET("/greet"), 
				(r)->(ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromObject(greet))));
	}*/
	@GetMapping("/greet")
	public Mono<String> greeta(){
		return Mono.just(greet);
	}
	
	@GetMapping("/")
	public Mono<Book> getBookById(Integer id){
		Book book = new Book();
		book.setId(id);
		book.setName("JAVA");
		book.setInfo("JAVABOOK");
		return Mono.just(book);
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GuQingBookStoreApp.class).web(WebApplicationType.REACTIVE).run(args);
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
