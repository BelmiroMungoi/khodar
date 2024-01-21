package com.bbm.khodar.service.impl;

import com.bbm.khodar.dto.request.TicketRequest;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.dto.response.TicketResponse;
import com.bbm.khodar.exception.BadRequestException;
import com.bbm.khodar.mapper.Mapper;
import com.bbm.khodar.model.Event;
import com.bbm.khodar.model.Ticket;
import com.bbm.khodar.repository.TicketRepository;
import com.bbm.khodar.service.EventService;
import com.bbm.khodar.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final Mapper mapper;
    private final EmailService emailService;
    private final EventService eventService;
    private final TicketRepository ticketRepository;

    @Override
    @Transactional
    public HttpResponse createTicket(TicketRequest request, Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (ticketRepository.existsByAttendeeEmailAndEvent(request.getAttendeeEmail(), event)) {
            throw new BadRequestException("Você já está inscrito pra esse evento! Verifique o seu email!");
        }
        Ticket ticketToBeSaved = Ticket.builder()
                .attendeeName(request.getAttendeeName())
                .attendeeEmail(request.getAttendeeEmail())
                .isChecked(false)
                .event(event)
                .build();
        ticketRepository.save(ticketToBeSaved);

        emailService.sendEmail(request.getAttendeeName(),
                request.getAttendeeEmail(),
                event.getTitle());

        return HttpResponse.builder()
                .responseCode(CREATED.value())
                .responseStatus(CREATED)
                .responseMessage("O ticket foi criado com sucesso!")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> findAllTickets() {
        return mapper.mapToTicketResponseList(ticketRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> findAllTicketsByEventId(Long eventId) {
        return mapper.mapToTicketResponseList(ticketRepository.findAllByEventId(eventId));
    }
}
