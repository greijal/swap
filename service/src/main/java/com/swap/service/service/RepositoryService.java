package com.swap.service.service;

import com.swap.client.GitHubClient;
import com.swap.service.dto.RepositoryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RepositoryService {

    private final Logger LOG = LoggerFactory.getLogger(RepositoryService.class);
    private final String TOPIC_NAME = "repository.info";


    private KafkaTemplate<String, RepositoryMessage> kafkaTemplate;
    private GitHubClient gitHubClient;

    @Autowired
    public RepositoryService(KafkaTemplate<String, RepositoryMessage> kafkaTemplate,
                             GitHubClient gitHubClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.gitHubClient = gitHubClient;
    }

    public boolean exist(String owner, String repo) {
        return gitHubClient.getRepository(owner, repo) != null;
    }

    public void info(String owner, String repository) {
        kafkaTemplate.send(TOPIC_NAME,new RepositoryMessage(owner,repository, new Date()));
    }
}
