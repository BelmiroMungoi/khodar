package com.bbm.khodar.dto.request;

import lombok.Data;

@Data
public class TicketRequest {

    private String attendee_name;
    private String attendee_email;
    private boolean isChecked;
}
