package com.mklubo.GithubRepoExplorer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorResponse {
        @JsonProperty("status")
        private int status;
        @JsonProperty("Message")
        private String message;

        public ErrorResponse(int status, String message) {
                this.status = status;
                this.message = message;
        }

        @Override
        public String toString() {
                try {
                        ObjectMapper mapper = new ObjectMapper();
                        return mapper.writeValueAsString(this);
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                }
                return super.toString();
        }

}
