package com.xebia.treewalaproject.entity;

import org.springframework.http.HttpStatus;

import java.util.List;

public class UserResponse {
        private HttpStatus status;
        private String message;
        private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
            this.message = message;
        }

    }
