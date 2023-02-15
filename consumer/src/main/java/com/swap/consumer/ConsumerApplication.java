package com.swap.consumer;

import com.swap.client.BuilderClient;
import com.swap.client.GitHubClient;
import com.swap.dto.RepositoryMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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

}
