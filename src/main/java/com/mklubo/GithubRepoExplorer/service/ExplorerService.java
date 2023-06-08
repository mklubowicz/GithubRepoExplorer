package com.mklubo.GithubRepoExplorer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mklubo.GithubRepoExplorer.model.BranchInfo;
import com.mklubo.GithubRepoExplorer.model.UserRepositoryData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorerService {
    private final GithubApiService githubApiService;

    public ExplorerService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public List<UserRepositoryData> getUserRepositoryData(String username) throws JsonProcessingException {
        List<UserRepositoryData> returnList = new ArrayList<>();
        String jsonResponse = githubApiService.getRepositoriesForAUser(username);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonResponse);
        for(JsonNode node : root) {
            boolean isForked = node.get("fork").asBoolean();
            if(!isForked) {
                String repositoryName = node.get("name").asText();
                String owner = node.get("owner").get("login").asText();
                returnList.add(new UserRepositoryData(
                        repositoryName,
                        owner,
                        new ArrayList<>()
                ));
            }
        }
        for(UserRepositoryData urd : returnList) {
            String branchJson = githubApiService.getBranchInfoOfRepository(username,urd.repositoryName());
            root = objectMapper.readTree(branchJson);
            for(JsonNode node : root) {
                String name = node.get("name").asText();
                String sha = node.get("commit").get("sha").asText();
                urd.branchInfo().add(new BranchInfo(name,sha));
            }
        }
        return returnList;
    }
}
