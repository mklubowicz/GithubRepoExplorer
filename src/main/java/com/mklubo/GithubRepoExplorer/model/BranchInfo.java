package com.mklubo.GithubRepoExplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BranchInfo(
   String name,
   @JsonProperty("sha")
   String lastCommitSha
) {}
