package com.mklubo.GithubRepoExplorer.exception;

import com.mklubo.GithubRepoExplorer.model.ErrorResponse;

public class UsernameNotExistsException extends RuntimeException{

    public UsernameNotExistsException() {
        super(new ErrorResponse(404,"User with this username does not exist").toString());
    }
    public UsernameNotExistsException(String message) {
        super(message);
    }
}
