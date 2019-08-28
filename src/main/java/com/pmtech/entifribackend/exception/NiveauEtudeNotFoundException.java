package com.pmtech.entifribackend.exception;

/**
 * NiveauEtudeNotFoundException
 */
public class NiveauEtudeNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -437859274015952211L;

    public NiveauEtudeNotFoundException(Long idNiv){
        super("Niveau Etude with code "+idNiv+" not found");
    }

    
}