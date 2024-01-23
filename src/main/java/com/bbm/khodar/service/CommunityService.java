package com.bbm.khodar.service;

import com.bbm.khodar.dto.request.AuthenticationRequest;
import com.bbm.khodar.dto.request.CommunityRequest;
import com.bbm.khodar.dto.response.CommunityResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.dto.response.TokenResponse;
import com.bbm.khodar.model.Community;

import java.util.List;

public interface CommunityService {

    HttpResponse create(CommunityRequest communityRequest);

    List<CommunityResponse> findAllCommunities();

    CommunityResponse findCommunityById(Long id);

    Community getCommunityById(Long id);

    HttpResponse update(CommunityRequest communityRequest, Long id);

    TokenResponse login(AuthenticationRequest request);
}
