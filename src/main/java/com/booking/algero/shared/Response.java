package com.booking.algero.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@SuperBuilder
public class Response {
    protected LocalDateTime timestamp = LocalDateTime.now();
    protected HttpStatus status;
    protected int statusCode = status.value();
    protected String message;
    protected Object data;



}
