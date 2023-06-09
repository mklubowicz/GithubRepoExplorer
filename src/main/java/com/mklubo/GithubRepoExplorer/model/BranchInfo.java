package com.mklubo.GithubRepoExplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


public class BranchInfo {
   @JsonProperty("name")
   private String name;
   private String sha;

   public BranchInfo(String name, String sha) {
      this.name = name;
      this.sha = sha;
   }

   @JsonProperty("commit")
   private void unpackNested(Map<String,Object> commit) {
      this.sha = (String) commit.get("sha");
   }

   @JsonProperty("sha")
   public String getSha() {
      return sha;
   }

}
