package com.easytickets.eventservice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private int status;
    private String error;
    private LocalDateTime timestamp;
    private List<FieldViolation> violations = new ArrayList<>();

    public void addViolation(String field, String message) {
        violations.add(new FieldViolation(field, message));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<FieldViolation> getViolations() {
        return violations;
    }

    public void setViolations(List<FieldViolation> violations) {
        this.violations = violations;
    }
}
