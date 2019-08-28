package com.pmtech.entifribackend.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy= InheritanceType.JOINED)
public class Information {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String title;
    protected String object;
    protected Date datPost;
    protected String description;

    /*
        0 default value
        1 for all users
        2 for all students
        3 for all teachers
     */
    protected int destinataire;



    //Relation between class manage

    @ManyToOne
    @JoinColumn(name = "admin_id")
    protected Admin admin;

    @OneToMany(mappedBy = "information")
    protected List<NiveauEtude> niveauEtudes = new ArrayList<>();

    @OneToMany(mappedBy = "information")
    protected List<Specialite> specialites = new ArrayList<>();

    @OneToMany(mappedBy = "information")
    protected List<Etudiant> etudiants = new ArrayList<>();

    @OneToMany(mappedBy = "information")
    protected List<Enseignant> enseignants = new ArrayList<>();
    
}