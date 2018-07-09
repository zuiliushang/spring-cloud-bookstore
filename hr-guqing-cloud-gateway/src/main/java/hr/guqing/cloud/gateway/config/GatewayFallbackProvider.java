package hr.guqing.cloud.gateway.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class GatewayFallbackProvider implements FallbackProvider{

	private Logger logger = LoggerFactory.getLogger(GatewayFallbackProvider.class);
	
	
	
	public GatewayFallbackProvider() {
		System.out.println("GatewayFallbackProvider init ..");
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		logger.error("gateway fallback reason {}", cause.getMessage(),cause);
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(("{\"code\":500, \"msg\":\"服务内部错误,请稍后重试\"}").getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				// TODO Auto-generated method stub
				return "ok";
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return 200;
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public String getRoute() {
		return "*";
	}
	
}
