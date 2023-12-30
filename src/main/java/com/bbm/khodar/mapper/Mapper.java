package com.bbm.khodar.mapper;

import com.bbm.khodar.dto.response.CommunityResponse;
import com.bbm.khodar.dto.response.EventResponse;
import com.bbm.khodar.model.Community;
import com.bbm.khodar.model.Event;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static ModelMapper modelMapper;

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

    public static EventResponse mapToEventResponse(Event event) {
        return modelMapper.map(event, EventResponse.class);
    }

    public static List<EventResponse> mapToEventResponseList(List<Event> events) {
        return events.stream().map(event ->
                mapToEventResponse(event)).collect(Collectors.toList());
    }
}
