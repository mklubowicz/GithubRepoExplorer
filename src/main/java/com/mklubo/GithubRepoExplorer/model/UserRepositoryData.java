package com.mklubo.GithubRepoExplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class UserRepositoryData {
        private String repositoryName;
        @JsonProperty("owner")
        private String owner;
        @JsonProperty("branch_info")
        private List<BranchInfo> branchInfo;

        public UserRepositoryData(String repositoryName, String owner, List<BranchInfo> branchInfo) {
                this.repositoryName = repositoryName;
                this.owner = owner;
                this.branchInfo = branchInfo;
        }

        @JsonProperty("owner")
        private void unpackNested(Map<String,String> owner) {
                this.owner = owner.get("login");
        }
        @JsonProperty("repository_name")
        public String getRepositoryName() {
                return repositoryName;
        }

        @JsonProperty("name")
        public void setRepositoryName(String repositoryName) {
                this.repositoryName = repositoryName;
        }

        public String getOwner() {
                return owner;
        }

        public void setBranchInfo(List<BranchInfo> branchInfo) {
                this.branchInfo = branchInfo;
        }
}
