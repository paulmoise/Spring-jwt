package com.pmtech.entifribackend.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NiveauEtude
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class NiveauEtude {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNivEtu;
    private String libNivEtu;

    @JsonIgnore
    @OneToMany(mappedBy = "niveauEtude")
    private List<Etudiant> etudiants = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "niveauEtude")
    private List<Matiere> matieres = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "information_id", insertable = true, updatable = true)
    private Information information;
    

}