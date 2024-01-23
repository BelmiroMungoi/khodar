package com.bbm.khodar.service.impl;

import com.bbm.khodar.dto.request.AuthenticationRequest;
import com.bbm.khodar.dto.request.CommunityRequest;
import com.bbm.khodar.dto.response.CommunityResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.dto.response.TokenResponse;
import com.bbm.khodar.exception.BadRequestException;
import com.bbm.khodar.exception.EntityNotFoundException;
import com.bbm.khodar.model.Community;
import com.bbm.khodar.model.Token;
import com.bbm.khodar.model.enums.Role;
import com.bbm.khodar.repository.CommunityRepository;
import com.bbm.khodar.repository.TokenRepository;
import com.bbm.khodar.security.JwtService;
import com.bbm.khodar.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final TokenRepository tokenRepository;
    private final CommunityRepository communityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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
                .password(passwordEncoder.encode(communityRequest.getPassword()))
                .role(Role.ADMIN)
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

    @Override
    public TokenResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var community = communityRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(community);
        saveUserToken(community, jwtToken);
        return TokenResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void saveUserToken(Community community, String jwtToken) {
        var token = Token.builder()
                .community(community)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
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
