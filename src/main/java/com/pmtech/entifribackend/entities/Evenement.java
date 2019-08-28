package com.pmtech.entifribackend.entities;

import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Evenement
 */
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Evenement extends Information {

    private Date datEven;
    private String Image;
    
}