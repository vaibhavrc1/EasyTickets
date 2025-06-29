package com.easytickets.eventservice.service.query;

import com.easytickets.eventservice.dto.EventResponse;
import com.easytickets.eventservice.exception.EventNotFoundException;
import com.easytickets.eventservice.mapper.EventMapper;
import com.easytickets.eventservice.model.EventEntity;
import com.easytickets.eventservice.repository.EventRepository;
import jdk.jfr.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EventQueryServiceImpl implements EventQueryService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventQueryServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventResponse getEventById(Long id) {
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(id);
        return optionalEventEntity.map(eventMapper::toEventResponse).orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
    }

    @Override
    public List<EventResponse> getEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<EventEntity> eventEntities = eventRepository.findAll(pageable);
        return eventEntities.stream().map(eventMapper::toEventResponse).toList();
    }
}
