package com.bbm.khodar.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityRequest {

    @NotBlank
    @Size(min = 3, max = 120)
    private String name;

    @NotBlank
    @Size(min = 3, max = 5000)
    private String description;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String website;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    private LocalDateTime createdAt;
}
