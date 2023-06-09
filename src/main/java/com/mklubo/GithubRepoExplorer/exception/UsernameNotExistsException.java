package com.mklubo.GithubRepoExplorer.exception;

public class UsernameNotExistsException extends RuntimeException{

    public UsernameNotExistsException() {
        super("User with this username does not exist");
    }
    public UsernameNotExistsException(String message) {
        super(message);
    }
}
