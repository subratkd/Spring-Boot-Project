package com.subrat.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}
	@Bean
	public RestTemplate geRestTemplate() {
		HttpComponentsClientHttpRequestFactory clientRequest = new HttpComponentsClientHttpRequestFactory();
		clientRequest.setConnectTimeout(3000);
		return new RestTemplate(clientRequest);
	}

}
