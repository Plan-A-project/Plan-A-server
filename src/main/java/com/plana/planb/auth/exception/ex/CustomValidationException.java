package com.plana.planb.auth.exception.ex;

import java.util.Map;
import lombok.Data;

@Data
public class CustomValidationException extends RuntimeException{

    private String message;
    private Map<String, String> errorMap;


    public CustomValidationException(String message, Map<String, String> errorMap) {
        this.message = message;
        this.errorMap = errorMap;
    }

}
