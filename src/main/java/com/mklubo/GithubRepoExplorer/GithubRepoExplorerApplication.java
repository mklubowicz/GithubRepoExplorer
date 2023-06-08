package com.mklubo.GithubRepoExplorer;

import com.mklubo.GithubRepoExplorer.config.HttpErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubRepoExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubRepoExplorerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.rootUri("https://api.github.com")
				.errorHandler(new HttpErrorHandler())
				.build();
	}
}
