package com.easytickets.eventservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class EventRequest {

    @NotBlank(message = "Event name is required")
    private String name;
    @NotBlank(message = "Event location is required")
    private String location;
    @NotNull(message = "Event start time is required")
    private LocalDateTime startTime;
    @NotNull(message = "Event end time is required")
    private LocalDateTime endTime;
    @Size(max = 500, message = "Event description must be less than 500 characters")
    private String description;
    @NotNull(message = "Event total seats is required")
    @Min(value = 1, message = "Event total seats must be greater than 0")
    private Integer totalSeats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

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
