package com.pmtech.entifribackend.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Specialite
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Specialite implements Serializable {

    @Id
    private String codSpe;

    private String libSpe;

    @JsonIgnore
    @OneToMany(mappedBy = "specialite")
    private List<Etudiant> etudiants = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "specialite")
    private List<Matiere> matieres = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "information_id", insertable = true, updatable = true)
    private Information information;

    
}