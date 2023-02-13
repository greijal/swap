package com.swap.service;

import com.swap.client.BuilderClient;
import com.swap.client.GitHubClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    public GitHubClient gitHubClient() {
        return BuilderClient.create();
    }

}
