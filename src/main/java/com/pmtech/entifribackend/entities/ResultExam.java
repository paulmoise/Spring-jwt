package com.pmtech.entifribackend.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ResultExam
 */
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResultExam  extends Information{
    
    private String description;
    private String fileName;
    

   // @OneToOne(mappedBy = "resultExam")
   // private InfoFile infoFile;
}