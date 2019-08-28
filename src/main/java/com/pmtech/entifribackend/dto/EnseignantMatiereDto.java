package com.pmtech.entifribackend.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * EnseignantMatiereDto
 */
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class EnseignantMatiereDto {

    private Long id;
    private String[] tabCodMat;
}