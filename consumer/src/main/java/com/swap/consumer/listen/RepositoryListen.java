package com.swap.consumer.listen;

import com.swap.client.GitHubClient;
import com.swap.client.dto.Contributor;
import com.swap.client.dto.Issues;
import com.swap.consumer.dto.RepositoryResponse;
import com.swap.dto.RepositoryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryListen {

    private final Logger LOG = LoggerFactory.getLogger(RepositoryListen.class);
    private final String TOPIC_NAME = "repository.info";
    private final String  WEB_HOOK_URL ="https://webhook.site/22c3b3f2-bd2b-40ac-bbba-09de5df0cd87";
    private final GitHubClient gitHubClient;
    private final RestTemplate restTemplate;

    @Autowired
    public RepositoryListen(GitHubClient gitHubClient, RestTemplate restTemplate) {
        this.gitHubClient = gitHubClient;
        this.restTemplate = restTemplate;
    }

    @KafkaListener(topics = {TOPIC_NAME})
    public void listen(RepositoryMessage message) {
        LOG.info("Get new message {}", message.toString());
        List<RepositoryResponse.IssuesResponse> issues = getIssues(message.getOwner(), message.getRepository())
                .parallelStream()
                .map(i-> new RepositoryResponse.IssuesResponse()
                            .setTitle(i.getTitle())
                            .setAuthor(i.getUser().getLogin())
                            .setLabels(i.getLabels().stream().map(Issues.Label::getName).collect(Collectors.toList()))
                ).collect(Collectors.toList());

        var contributor = getContributor(message.getOwner(), message.getRepository())
                .parallelStream()
                .map(c->new RepositoryResponse.ContributorResponse()
                        .setTotalCommit(c.getTotal())
                        .setAuthor(c.getAuthor().getLogin())).
                collect(Collectors.toList());

        var response = new RepositoryResponse()
                .setOwner(message.getOwner())
                .setRepository(message.getRepository())
                .setIssues(issues)
                .setContributors(contributor);

        restTemplate
                .postForEntity(WEB_HOOK_URL, response,String.class);

    }

    private List<Issues> getIssues(String owner, String repo) {
        var page = 1;
        return getIssuesPage(owner, repo, page, new ArrayList<Issues>());
    }

    private List<Issues> getIssuesPage(String owner, String repo, int page, List<Issues> allIssues) {
        LOG.info("Getting Issues. Owner {} Repo {} Pade{}", owner, repo, page);
        var gitResponse = gitHubClient.getIssues(owner, repo, page);
        if (gitResponse.isEmpty()) {
            return allIssues;
        }
        allIssues.addAll(gitResponse);
        return getIssuesPage(owner, repo, page+1, allIssues);
    }


    private List<Contributor> getContributor(String owner, String repo) {
        return gitHubClient.getContributor(owner, repo);
    }

}
