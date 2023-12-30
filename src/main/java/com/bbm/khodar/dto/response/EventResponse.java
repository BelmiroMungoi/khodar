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
public class EventResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String start_time;
    private String end_time;
    private int eventLimit;
}
