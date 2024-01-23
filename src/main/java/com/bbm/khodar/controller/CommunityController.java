package com.bbm.khodar.controller;

import com.bbm.khodar.dto.request.AuthenticationRequest;
import com.bbm.khodar.dto.request.CommunityRequest;
import com.bbm.khodar.dto.response.CommunityResponse;
import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.dto.response.TokenResponse;
import com.bbm.khodar.model.Token;
import com.bbm.khodar.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/communities")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/")
    public ResponseEntity<HttpResponse> createCommunity(@Valid @RequestBody CommunityRequest request) {
        return new ResponseEntity<>(communityService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommunityResponse>> findAllCommunities() {
        return ResponseEntity.ok(communityService.findAllCommunities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse> findCommunityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(communityService.findCommunityById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateCommunity(@Valid @RequestBody CommunityRequest request,@PathVariable("id") Long id) {
        return ResponseEntity.ok(communityService.update(request, id));
    }
}
