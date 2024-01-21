package com.bbm.khodar.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;


@Data
public class EventRequest {

    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

    @NotBlank
    @Size(min = 3, max = 5000)
    private String description;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    @NotNull
    private int eventLimit;
}
