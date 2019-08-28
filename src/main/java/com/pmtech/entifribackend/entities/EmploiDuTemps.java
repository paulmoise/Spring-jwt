package com.pmtech.entifribackend.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * EmploiDuTemps
 */
@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class EmploiDuTemps extends Information {

    private String periode;
    private Date datPost;
    private String fileName;

    @OneToOne(mappedBy = "emploiDuTemps")
    private InfoFile infoFile;
}