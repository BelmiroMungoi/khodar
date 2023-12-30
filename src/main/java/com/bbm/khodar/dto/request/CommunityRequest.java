package com.bbm.khodar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CommunityRequest {

    private String name;
    private String description;
    private String email;
    private String website;
    private String password;
    private LocalDateTime createdAt;
}
