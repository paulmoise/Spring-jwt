package com.pmtech.entifribackend.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * admin
 */
@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Etudiant extends AppUser {

    private Long numMtle;


    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "codSpe", insertable = true, updatable = true)
    private Specialite specialite;


    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "idNivEtu", insertable = true, updatable = true)
    private NiveauEtude niveauEtude;

    @ManyToOne
    @JoinColumn(name = "information_id", insertable = true, updatable = true)
    private Information information;
    
    
}