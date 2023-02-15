package com.swap.consumer.listen;

import com.swap.client.GitHubClient;
import com.swap.client.dto.Issues;
import com.swap.dto.RepositoryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryListen {

    private final Logger LOG = LoggerFactory.getLogger(RepositoryListen.class);
    private final String TOPIC_NAME = "repository.info";
    private GitHubClient gitHubClient;


    @KafkaListener(topics = {TOPIC_NAME})
    public void listen(RepositoryMessage message) {
        LOG.info("Get new message {}", message.toString());
        var issues = getIssues(message.getOwner(), message.getRepository());


        System.out.println("Received User information : " + message);
    }

    private List<Issues> getIssues(String owner, String repo){
        var page =1;
        return getIssuesPage(owner,repo,page,new ArrayList<Issues>());
    }

    private List<Issues> getIssuesPage(String owner, String repo, int page, List<Issues> allIssues ){
        LOG.info("Getting Issues. Owner {} Repo {} Pade{}",owner,repo,page);
        var gitResponse = gitHubClient.getIssues(owner,repo,1);
        if(gitResponse.isEmpty()){
            return allIssues;
        }
        allIssues.addAll(gitResponse);
        return getIssuesPage(owner,repo,page++,allIssues);
    }




}
