package com.lebanking.api.common.exception;

import java.io.Serial;

public class UnableSendEmailException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public UnableSendEmailException(String message) {
        super(message);
    }
}
