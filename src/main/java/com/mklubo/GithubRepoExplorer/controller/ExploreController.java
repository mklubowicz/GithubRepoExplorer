package com.mklubo.GithubRepoExplorer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mklubo.GithubRepoExplorer.exception.UsernameNotExistsException;
import com.mklubo.GithubRepoExplorer.model.ErrorResponse;
import com.mklubo.GithubRepoExplorer.model.UserRepositoryData;
import com.mklubo.GithubRepoExplorer.service.ExplorerService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getUserRepositoryData(@PathVariable(name = "username") String username) {
        try {
            List<UserRepositoryData> userRepositoryData = explorerService.getUserRepositoryData(username);
            return ResponseEntity.ok(userRepositoryData);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage()));
        } catch (UsernameNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
