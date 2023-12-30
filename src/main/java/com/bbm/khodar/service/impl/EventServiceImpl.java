package com.bbm.khodar.service.impl;

import com.bbm.khodar.dto.request.EventRequest;
import com.bbm.khodar.dto.response.EventResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.model.Community;
import com.bbm.khodar.model.Event;
import com.bbm.khodar.repository.EventRepository;
import com.bbm.khodar.service.CommunityService;
import com.bbm.khodar.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bbm.khodar.mapper.Mapper.mapToEventResponseList;
import static com.bbm.khodar.utils.KhodarUtils.EVENT_CREATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CommunityService communityService;

    @Override
    public HttpResponse createEvent(EventRequest eventRequest, Long communityId) {
        Community community = communityService.getCommunityById(communityId);

        Event event = Event.builder()
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .eventLimit(eventRequest.getEventLimit())
                .start_time(eventRequest.getStart_time())
                .end_time(eventRequest.getEnd_time())
                .event_date(eventRequest.getDate())
                .community(community)
                .build();
      eventRepository.save(event);

        return httpResponse(HttpStatus.CREATED,
                EVENT_CREATED_SUCCESSFULLY);
    }

    @Override
    public List<EventResponse> findAllEvents() {
        return mapToEventResponseList(eventRepository.findAll());
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
