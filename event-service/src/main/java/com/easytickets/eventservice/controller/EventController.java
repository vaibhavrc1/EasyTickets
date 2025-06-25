package com.easytickets.eventservice.controller;

import com.easytickets.eventservice.dto.EventRequest;
import com.easytickets.eventservice.dto.EventResponse;
import com.easytickets.eventservice.dto.PatchEventRequest;
import com.easytickets.eventservice.service.command.EventCommandServiceImpl;
import com.easytickets.eventservice.service.query.EventQueryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Validated
public class EventController {

    private final EventQueryService eventQueryService;
    private final EventCommandServiceImpl eventCommandService;

    public EventController(EventQueryService eventQueryService, EventCommandServiceImpl eventCommandService) {
        this.eventQueryService = eventQueryService;
        this.eventCommandService = eventCommandService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        EventResponse savedEventResponse = this.eventCommandService.createEvent(eventRequest);
        return new ResponseEntity<>(savedEventResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable @Positive Long id) {
        EventResponse eventResponse = eventQueryService.getEventById(id);
        return new ResponseEntity<>(eventResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable @Positive Long id) {
        eventCommandService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="10") int size) {
        List<EventResponse> eventResponses = eventQueryService.getEvents(page, size);
        return new ResponseEntity<>(eventResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable @Positive Long id, @Valid @RequestBody EventRequest eventRequest) {
        EventResponse updatedEventResponse = eventCommandService.updateEvent(id, eventRequest);
        return new ResponseEntity<>(updatedEventResponse, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventResponse> partialUpdateEvent(@PathVariable @Positive Long id, @Valid @RequestBody PatchEventRequest eventRequest) {
        EventResponse updatedEventResponse = eventCommandService.patchEvent(id, eventRequest);
        return new ResponseEntity<>(updatedEventResponse, HttpStatus.OK);
    }
}
