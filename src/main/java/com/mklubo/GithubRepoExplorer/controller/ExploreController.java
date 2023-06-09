package com.mklubo.GithubRepoExplorer.controller;

import com.mklubo.GithubRepoExplorer.model.UserRepositoryData;
import com.mklubo.GithubRepoExplorer.service.ExplorerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/explorer")
public class ExploreController {
    private final ExplorerService explorerService;

    public ExploreController(ExplorerService explorerService) {
        this.explorerService = explorerService;
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserRepositoryData>> getUserRepositoryData(@PathVariable(name = "username") String username) {
        List<UserRepositoryData> userRepositoryData = explorerService.getUserRepositoryData(username);
        return ResponseEntity.ok(userRepositoryData);
    }


}
