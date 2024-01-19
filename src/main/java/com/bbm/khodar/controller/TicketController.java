package com.bbm.khodar.controller;

import com.bbm.khodar.dto.request.TicketRequest;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.dto.response.TicketResponse;
import com.bbm.khodar.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/{id}")
    public ResponseEntity<HttpResponse> create(@RequestBody TicketRequest request,
                                               @PathVariable("id") Long eventId) {
        return new ResponseEntity<>(ticketService.createTicket(request,eventId), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findAllTickets() {
        return ResponseEntity.ok(ticketService.findAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TicketResponse>> findAllTicketsByEventId(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(ticketService.findAllTicketsByEventId(eventId));
    }
}
