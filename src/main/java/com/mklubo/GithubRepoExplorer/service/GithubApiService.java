package com.mklubo.GithubRepoExplorer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class GithubApiService {
    private final RestTemplate restTemplate;

    public GithubApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRepositoriesForAUser(String username) {
        String uri = String.format("/users/%s/repos",username);
        return restTemplate.getForObject(uri, String.class);
    }

    public String getBranchInfoOfRepository(String username, String repositoryName) {
        String uri = String.format("/repos/%s/%s/branches",username,repositoryName);
        return restTemplate.getForObject(uri,String.class);
    }
}
