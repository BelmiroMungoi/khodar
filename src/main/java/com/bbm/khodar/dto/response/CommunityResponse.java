package com.bbm.khodar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityResponse {

    private Long id;
    private String name;
    private String description;
    private String email;
    private String website;
    private LocalDateTime createdAt;

}
