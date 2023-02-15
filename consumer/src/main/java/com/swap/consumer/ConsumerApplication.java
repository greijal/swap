package com.swap.consumer;

import com.swap.client.BuilderClient;
import com.swap.client.GitHubClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ConsumerApplication {
    @Value("${git.token}")
    private String gitToken;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public GitHubClient gitHubClient() {
        return BuilderClient.create(gitToken);
    }

    @Bean
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }

}
