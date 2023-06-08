package com.mklubo.GithubRepoExplorer.config;

import com.mklubo.GithubRepoExplorer.exception.UsernameNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class HttpErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UsernameNotExistsException();
            }
    }
}
