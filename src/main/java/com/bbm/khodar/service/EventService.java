package com.bbm.khodar.service;

import com.bbm.khodar.dto.request.EventRequest;
import com.bbm.khodar.dto.response.EventResponse;
import com.bbm.khodar.dto.response.HttpResponse;

import java.util.List;

public interface EventService {

    HttpResponse createEvent(EventRequest eventRequest, Long communityId);
    List<EventResponse> findAllEvents();
}
