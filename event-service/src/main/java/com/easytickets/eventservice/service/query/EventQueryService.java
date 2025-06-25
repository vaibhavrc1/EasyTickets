package com.easytickets.eventservice.service.query;

import com.easytickets.eventservice.dto.EventResponse;

import java.util.List;

public interface EventQueryService {

    EventResponse getEventById(Long id);

    List<EventResponse> getEvents(int page, int size);
}
