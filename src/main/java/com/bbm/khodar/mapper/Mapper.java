package com.bbm.khodar.mapper;

import com.bbm.khodar.dto.response.CommunityResponse;
import com.bbm.khodar.dto.response.EventResponse;
import com.bbm.khodar.dto.response.TicketResponse;
import com.bbm.khodar.model.Community;
import com.bbm.khodar.model.Event;
import com.bbm.khodar.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class Mapper {

    private final ModelMapper modelMapper;

    public static CommunityResponse mapToCommunityResponse(Community community) {
        CommunityResponse communityResponse = new CommunityResponse();
        communityResponse.setId(community.getId());
        communityResponse.setName(community.getName());
        communityResponse.setDescription(community.getDescription());
        communityResponse.setEmail(community.getEmail());
        communityResponse.setWebsite(community.getWebsite());
        communityResponse.setCreatedAt(community.getCreatedAt());

        return communityResponse;
    }

    public static List<CommunityResponse> mapToCommunityResponseList(List<Community> communities) {
        List<CommunityResponse> communityResponses = new ArrayList<>();
        for(Community community: communities) {
            communityResponses.add(mapToCommunityResponse(community));
        }
        return communityResponses;
    }

    public EventResponse mapToEventResponse(Event event) {
        return modelMapper.map(event, EventResponse.class);
    }

    public List<EventResponse> mapToEventResponseList(List<Event> events) {
        return events.stream().map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse mapToTicketResponse(Ticket ticket) {
        return modelMapper.map(ticket, TicketResponse.class);
    }

    public List<TicketResponse> mapToTicketResponseList(List<Ticket> tickets) {
        return tickets.stream().map(this::mapToTicketResponse)
                .collect(Collectors.toList());
    }
}
