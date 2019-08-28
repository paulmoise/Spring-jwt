package com.pmtech.entifribackend.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * AppUser
 */

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;
    protected String nom;
    protected String prenom;
    @Column(unique = true)
    protected String username;
    protected String defaultPassword;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String CIN;
    protected char sexe;
    protected boolean active;

    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date dateNaissance;



    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date activationDate;

    @Temporal(TemporalType.DATE)
    protected Date updateDate;
    protected String lieuNaissance;
    protected String photoName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();


    @Transient
    protected MultipartFile file;


}