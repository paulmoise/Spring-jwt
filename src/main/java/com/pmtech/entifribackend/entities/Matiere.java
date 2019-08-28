package com.pmtech.entifribackend.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


/**
 * Matiere
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Matiere {

    @Id
    private String codMat;

    private String libMat;
    private double credit;

    @JsonIgnore
    @Transient
    @OneToMany(mappedBy = "matiere")
    private List<Fichier> fichiers = new ArrayList<>();

    @JsonIgnore
    @Transient
    private List<MultipartFile> files = new ArrayList<>();

    @JsonIgnore
    @Transient
    private List<String> removeFiles = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "codSpe", insertable = true, updatable = true)
    private Specialite specialite;


    @JsonIgnore
    @JoinColumn(name = "idNivEtu", insertable = true, updatable = true)
    @ManyToOne(cascade= CascadeType.ALL)
    private NiveauEtude niveauEtude;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY,
    optional = false)
    @JoinColumn(name = "enseignant_id", insertable = true, updatable = true)
    private Enseignant enseignant;
}
