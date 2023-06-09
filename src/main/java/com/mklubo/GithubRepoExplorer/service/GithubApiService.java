package com.mklubo.GithubRepoExplorer.service;

import com.mklubo.GithubRepoExplorer.model.BranchInfo;
import com.mklubo.GithubRepoExplorer.model.UserRepositoryData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GithubApiService {
    private final WebClient webClient;

    public GithubApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public UserRepositoryData[] getRepositoriesForUser(String username) {
        String uri = String.format("/users/%s/repos",username);
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(UserRepositoryData[].class)
                .block();
    }
    public BranchInfo[] getBranchInfoOfRepository(String username, String repositoryName) {
        String uri = String.format("/repos/%s/%s/branches",username,repositoryName);
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(BranchInfo[].class)
                .block();
    }
}
