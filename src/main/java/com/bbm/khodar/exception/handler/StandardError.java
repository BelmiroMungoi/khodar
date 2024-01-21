package com.bbm.khodar.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StandardError {

    private int code;
    private HttpStatus status;
    private OffsetDateTime time;
    private String title;
    private String path;
    private List<ValidationError> fields;

    @Data
    @AllArgsConstructor
    public static class ValidationError {

        private String fieldName;
        private String message;
    }
}
