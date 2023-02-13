package com.swap.consumer.listen;

import com.swap.dto.RepositoryMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RepositoryListen {
    private final String TOPIC_NAME = "repository.info";

    @KafkaListener(topics = {TOPIC_NAME})
    public void listen(RepositoryMessage message) {

        System.out.println("Received User information : " + message);
    }
}
