package com.pmtech.entifribackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * SpecialiteNiveauEtudeDto
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SpecialiteNiveauEtudeDto {

    private String codSpe;
    private Long idNivEtu;
    private MultipartFile file;
    
}