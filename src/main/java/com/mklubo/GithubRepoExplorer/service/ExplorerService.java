package com.mklubo.GithubRepoExplorer.service;

import com.mklubo.GithubRepoExplorer.model.BranchInfo;
import com.mklubo.GithubRepoExplorer.model.UserRepositoryData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ExplorerService {
    private final GithubApiService githubApiService;

    public ExplorerService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public List<UserRepositoryData> getUserRepositoryData(String username) {
        List<UserRepositoryData> repositoriesForAUser = Arrays.stream(githubApiService.getRepositoriesForUser(username)).toList();

        repositoriesForAUser
                .forEach(data -> {
                    List<BranchInfo> branchInfos = Arrays.stream(githubApiService.getBranchInfoOfRepository(data.getOwner(), data.getRepositoryName())).toList();
                    data.setBranchInfo(branchInfos);
                });
        return repositoriesForAUser;
    }
}
