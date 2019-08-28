package com.pmtech.entifribackend.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ErrorMessage
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ErrorMessage {

    private String message;
    private int status;
    private Date timestamp;
    
}
