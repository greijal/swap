package com.swap.service.service;

import com.swap.client.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    private GitHubClient gitHubClient;

    @Autowired
    public RepositoryService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public boolean exist(String owner, String repo) {
        return gitHubClient.getRepository(owner, repo) != null;
    }

}
