package com.mklubo.GithubRepoExplorer.config;

import com.mklubo.GithubRepoExplorer.model.ErrorResponse;
import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatusCode status,
                                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(),
                 "Unsupported media type. Acceptable media types: "+ex.getSupportedMediaTypes());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).headers(responseHeaders).body(errorResponse);
    }
}
