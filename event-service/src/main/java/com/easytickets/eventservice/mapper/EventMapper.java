package com.easytickets.eventservice.mapper;

import com.easytickets.eventservice.dto.EventRequest;
import com.easytickets.eventservice.dto.EventResponse;
import com.easytickets.eventservice.dto.PatchEventRequest;
import com.easytickets.eventservice.model.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    
    @Mapping(target = "availableSeats", ignore = true)
    @Mapping(target = "id", ignore = true)
    EventEntity toEventEntity(EventRequest eventRequest);
    
    @AfterMapping
    default void setAvailableSeats(EventRequest source, @MappingTarget EventEntity target) {
        if (source.getTotalSeats() != null) {
            target.setAvailableSeats(source.getTotalSeats());
        }
    }

    EventResponse toEventResponse(EventEntity eventEntity);

    @Mapping(target = "availableSeats", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEventEntity(EventRequest eventRequest, @MappingTarget EventEntity existingEventEntity);

    @Mapping(target = "availableSeats", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    void patchEventEntity(PatchEventRequest eventRequest, @MappingTarget EventEntity existingEventEntity);
}
