package com.pmtech.entifribackend.exception;

import java.util.Date;

import com.pmtech.entifribackend.response.ErrorMessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ExceptionHandler
 */
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UtilisateurServiceException.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null)
            errorMessageDescription = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription, HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MatiereNotFoundException.class, SpecialiteNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex, WebRequest request){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null)
            errorMessageDescription = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription, HttpStatus.NOT_FOUND.value(), new Date());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


    
}