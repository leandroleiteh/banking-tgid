package com.lebanking.api.common.exception;

import java.io.Serial;

public class WebhookSiteException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public WebhookSiteException(String message) {
        super(message);
    }
}
