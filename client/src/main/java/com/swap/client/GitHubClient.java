package com.swap.client;

import com.swap.client.dto.Contributor;
import com.swap.client.dto.Issues;
import com.swap.client.dto.Repository;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers(value = {"Accept: application/vnd.github+json", "X-GitHub-Api-Version:2022-11-28"})
public interface GitHubClient {
    @RequestLine("GET /repos/{owner}/{repo}")
    Repository getRepository(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("GET /repos/{owner}/{repo}/issues?page={page}")
    List<Issues> getIssues(@Param("owner") String owner,
                           @Param("repo") String repo, @Param("page") int page);

    @RequestLine("GET /repos/{owner}/{repo}/stats/contributors")
    List<Contributor> getContributor(@Param("owner") String owner,
                                     @Param("repo") String repo);
}
