package com.easytickets.eventservice.service.command;

import com.easytickets.eventservice.dto.EventRequest;
import com.easytickets.eventservice.dto.EventResponse;
import com.easytickets.eventservice.dto.PatchEventRequest;
import com.easytickets.eventservice.exception.EventNotFoundException;
import com.easytickets.eventservice.mapper.EventMapper;
import com.easytickets.eventservice.model.EventEntity;
import com.easytickets.eventservice.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EventCommandServiceImpl implements EventCommandService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public EventCommandServiceImpl(EventMapper eventMapper, EventRepository eventRepository) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        EventEntity eventEntity = eventMapper.toEventEntity(eventRequest);
        EventEntity savedEventEntity = eventRepository.save(eventEntity);
        return eventMapper.toEventResponse(savedEventEntity);
    }

    @Override
    public void deleteEventById(Long id) {
        if(!eventRepository.existsById(id)) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        EventEntity existingEventEntity = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
        eventMapper.updateEventEntity(eventRequest, existingEventEntity);
        return eventMapper.toEventResponse(eventRepository.save(existingEventEntity));
    }

    @Override
    public EventResponse patchEvent(Long id, PatchEventRequest patchEventRequest) {
        EventEntity existingEventEntity = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
        eventMapper.patchEventEntity(patchEventRequest, existingEventEntity);
        return eventMapper.toEventResponse(eventRepository.save(existingEventEntity));
    }
}
