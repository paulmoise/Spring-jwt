package com.pmtech.entifribackend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * admin
 */
@Entity @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Enseignant extends AppUser {

    private Long numMtle;
    private String grade;


    @JsonIgnore
    @OneToMany(mappedBy = "enseignant")
    private List<Matiere> matieres = new ArrayList<>();


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "information_id", insertable = true, updatable = true)
    private Information information;
    
}