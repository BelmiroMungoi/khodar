package com.bbm.khodar.service.impl;

import com.bbm.khodar.dto.request.CommunityRequest;
import com.bbm.khodar.dto.response.CommunityResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.exception.BadRequestException;
import com.bbm.khodar.exception.EntityNotFoundException;
import com.bbm.khodar.model.Community;
import com.bbm.khodar.repository.CommunityRepository;
import com.bbm.khodar.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.bbm.khodar.mapper.Mapper.mapToCommunityResponse;
import static com.bbm.khodar.mapper.Mapper.mapToCommunityResponseList;
import static com.bbm.khodar.utils.KhodarUtils.ACCOUNT_CREATED_SUCCESSFULLY;
import static com.bbm.khodar.utils.KhodarUtils.ACCOUNT_UPDATED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Override
    @Transactional
    public HttpResponse create(CommunityRequest communityRequest) {
        if (communityRepository.existsByNameOrEmail(communityRequest.getName(),
                communityRequest.getEmail())) {
            throw new BadRequestException("Erro ao criar comunidade! Nome e Email já foram usados!");
        }
        Community community = Community.builder()
                .name(communityRequest.getName())
                .email(communityRequest.getEmail())
                .description(communityRequest.getDescription())
                .website(communityRequest.getWebsite())
                .password(communityRequest.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
        communityRepository.save(community);

        return httpResponse(HttpStatus.CREATED,
                ACCOUNT_CREATED_SUCCESSFULLY);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityResponse> findAllCommunities() {
        List<Community> communities = communityRepository.findAll();
        return mapToCommunityResponseList(communities);
    }

    @Override
    @Transactional(readOnly = true)
    public CommunityResponse findCommunityById(Long id) {
        var community = communityRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("A comunidade inserida não foi encontrada"));
        return mapToCommunityResponse(community);
    }

    @Override
    @Transactional(readOnly = true)
    public Community getCommunityById(Long id) {
        return communityRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("A comunidade inserida não foi encontrada!"));
    }

    @Override
    @Transactional
    public HttpResponse update(CommunityRequest communityRequest, Long id) {
        Community community = getCommunityById(id);
        community.setName(communityRequest.getName());
        community.setDescription(communityRequest.getDescription());
        community.setEmail(communityRequest.getEmail());
        community.setWebsite(communityRequest.getWebsite());
        communityRepository.save(community);

        return httpResponse(HttpStatus.OK,
                ACCOUNT_UPDATED_SUCCESSFULLY);
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
