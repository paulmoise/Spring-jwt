package com.pmtech.entifribackend.exception;

/**
 * SpecialiteNotFoundException
 */
public class SpecialiteNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -437859274015952211L;
    
    public SpecialiteNotFoundException(String codSpe){
        super("Specialite with code "+codSpe+" not found");
    }

    
}