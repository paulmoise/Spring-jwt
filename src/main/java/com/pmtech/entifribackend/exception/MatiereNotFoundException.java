package com.pmtech.entifribackend.exception;

/**
 * MatiereNotFoundException
 */
public class MatiereNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -437859274015952211L;

    public MatiereNotFoundException(String codMat){
        super("matiere with code "+codMat+" not found");
    }

    
}