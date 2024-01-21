package com.bbm.khodar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketRequest {

    @NotBlank
    @Size(min = 3, max = 120)
    private String attendeeName;

    @Email
    @NotBlank
    private String attendeeEmail;

    private boolean isChecked;
}
