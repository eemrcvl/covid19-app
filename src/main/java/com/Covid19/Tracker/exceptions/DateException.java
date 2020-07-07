package com.Covid19.Tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateException extends RuntimeException {
    public DateException(String msg){
        super(msg);
    }
    public DateException(String msg, Throwable cause){
        super(msg,cause);
    }
}
