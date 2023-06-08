package com.mklubo.GithubRepoExplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserRepositoryData (
        @JsonProperty("repository_name")
        String repositoryName,
        @JsonProperty("owner")
        String ownerLogin,
        @JsonProperty("branch_info")
        List<BranchInfo> branchInfo
) {}
