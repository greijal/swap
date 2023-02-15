package com.swap.service.service;

import com.swap.client.GitHubClient;
import com.swap.dto.RepositoryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;

@Service
public class RepositoryService {

    private final Logger LOG = LoggerFactory.getLogger(RepositoryService.class);
    private final String TOPIC_NAME = "repository.info";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final GitHubClient gitHubClient;

    @Autowired
    public RepositoryService(KafkaTemplate<String, Object> kafkaTemplate,
                             GitHubClient gitHubClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.gitHubClient = gitHubClient;
    }

    public boolean exist(String owner, String repo) {
        return gitHubClient.getRepository(owner, repo) != null;
    }

    public void info(String owner, String repository) {

        var message = new RepositoryMessage(owner, repository, new Date());

        kafkaTemplate.send(TOPIC_NAME, message)
                .addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        LOG.error("Error send message. " + message);
                        throw new RuntimeException(ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        LOG.info("Post new message with success.");
                        LOG.debug(result.toString());
                    }
                });
    }
}
