package com.easytickets.eventservice.service.command;

import com.easytickets.eventservice.dto.EventRequest;
import com.easytickets.eventservice.dto.EventResponse;
import com.easytickets.eventservice.dto.PatchEventRequest;

public interface EventCommandService {

    EventResponse createEvent(EventRequest eventRequest);

    void deleteEventById(Long id);

    EventResponse updateEvent(Long id, EventRequest eventRequest);

    EventResponse patchEvent(Long id, PatchEventRequest patchEventRequest);
}
