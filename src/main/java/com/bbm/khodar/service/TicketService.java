package com.bbm.khodar.service;

import com.bbm.khodar.dto.request.TicketRequest;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {

    HttpResponse createTicket(TicketRequest request, Long eventId);

    List<TicketResponse> findAllTickets();

    List<TicketResponse> findAllTicketsByEventId(Long eventId);
}
