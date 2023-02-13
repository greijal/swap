package com.swap.client;

import com.swap.client.dto.Repository;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers(value = {"Accept: application/vnd.github+json", "X-GitHub-Api-Version:2022-11-28"})
public interface GitHubClient {
    @RequestLine("GET /repos/{owner}/{repo}")
    Repository getRepository(@Param("owner") String owner, @Param("repo") String repo);


}
