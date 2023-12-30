package com.bbm.khodar.dto.request;

import lombok.Data;

import java.time.LocalDate;


@Data
public class EventRequest {

    private String title;
    private String description;
    private LocalDate date;
    private String start_time;
    private String end_time;
    private int eventLimit;
}
