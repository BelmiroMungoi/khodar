package com.bbm.khodar.service.impl;

import com.bbm.khodar.dto.request.EventRequest;
import com.bbm.khodar.dto.response.EventResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.exception.EntityNotFoundException;
import com.bbm.khodar.mapper.Mapper;
import com.bbm.khodar.model.Community;
import com.bbm.khodar.model.Event;
import com.bbm.khodar.repository.EventRepository;
import com.bbm.khodar.service.CommunityService;
import com.bbm.khodar.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.bbm.khodar.utils.KhodarUtils.EVENT_CREATED_SUCCESSFULLY;
import static com.bbm.khodar.utils.KhodarUtils.EVENT_UPDATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CommunityService communityService;
    private final Mapper mapper;

    @Override
    @Transactional
    public HttpResponse createEvent(EventRequest eventRequest, Long communityId) {
        Community community = communityService.getCommunityById(communityId);

        Event event = Event.builder()
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .eventLimit(eventRequest.getEventLimit())
                .startTime(eventRequest.getStartTime())
                .endTime(eventRequest.getEndTime())
                .eventDate(eventRequest.getDate())
                .community(community)
                .build();
        eventRepository.save(event);

        return httpResponse(HttpStatus.CREATED,
                EVENT_CREATED_SUCCESSFULLY);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> findAllEvents() {
        return mapper.mapToEventResponseList(eventRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse findEventById(Long id) {
        var event = eventRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("O evento inserido não foi encontrado"));
        return mapper.mapToEventResponse(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("O evento inserido não foi encontrado"));
    }

    @Override
    @Transactional
    public HttpResponse update(EventRequest eventRequest, Long eventId) {
        Event event = getEventById(eventId);
        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setEventDate(eventRequest.getDate());
        event.setEventLimit(eventRequest.getEventLimit());
        event.setStartTime(eventRequest.getStartTime());
        event.setEndTime(event.getEndTime());
        eventRepository.save(event);

        return httpResponse(HttpStatus.OK,
                EVENT_UPDATED_SUCCESSFULLY);
    }

    private static HttpResponse httpResponse(HttpStatus status, String message) {
        return HttpResponse.builder()
                .responseCode(status.value())
                .responseStatus(status)
                .responseMessage(message)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
