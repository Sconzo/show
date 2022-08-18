package com.restful.webservices.exception;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@RequiredArgsConstructor
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -4197325672075662140L;

    private final String error;
    private final String description;
}
