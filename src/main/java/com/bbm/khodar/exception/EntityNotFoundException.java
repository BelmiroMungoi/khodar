package com.bbm.khodar.exception;

public class EntityNotFoundException extends BadRequestException {

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
