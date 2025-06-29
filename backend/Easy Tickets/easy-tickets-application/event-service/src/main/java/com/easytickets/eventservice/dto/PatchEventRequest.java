package com.easytickets.eventservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatchEventRequest {

    @Size(max = 500, message = "Event description must be less than 500 characters")
    private String description;
    @NotNull(message = "Event total seats is required")
    @Min(value = 1, message = "Event total seats must be greater than 0")
    private Integer totalSeats;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }
}
