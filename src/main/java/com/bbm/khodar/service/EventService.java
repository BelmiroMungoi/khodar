package com.bbm.khodar.service;

import com.bbm.khodar.dto.request.EventRequest;
import com.bbm.khodar.dto.response.EventResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.model.Event;

import java.util.List;

public interface EventService {

    HttpResponse createEvent(EventRequest eventRequest, Long communityId);

    List<EventResponse> findAllEvents();

    List<EventResponse> findAllEventsByCommunityId(Long communityId);

    EventResponse findEventById(Long id);

    Event getEventById(Long id);

    HttpResponse update(EventRequest eventRequest, Long eventId);
}
