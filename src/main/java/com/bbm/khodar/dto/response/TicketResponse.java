package com.bbm.khodar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private Long id;
    private String attendee_name;
    private String attendee_email;
    private boolean isChecked;
    private EventDetails eventDetails;
}
