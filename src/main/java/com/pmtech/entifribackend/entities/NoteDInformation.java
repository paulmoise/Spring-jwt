package com.pmtech.entifribackend.entities;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NoteDInformation
 */
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NoteDInformation extends Information {

    private String ref;
    private String object;
    private  String fileName;
}