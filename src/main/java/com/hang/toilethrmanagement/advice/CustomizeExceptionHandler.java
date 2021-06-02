package com.hang.toilethrmanagement.advice;

import com.hang.toilethrmanagement.exception.CustomizeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    public Map<String, Object> handle(CustomizeException customizeException) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", customizeException.getCode());
        result.put("msg", customizeException.getMessage());

        return result;
    }
}
