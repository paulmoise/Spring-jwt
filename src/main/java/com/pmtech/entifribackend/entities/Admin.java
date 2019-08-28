package com.pmtech.entifribackend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Admin
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Admin extends AppUser {

    private String poste;


    //Relation Manage
    @OneToMany(mappedBy = "admin")
    private List<Information> informations = new ArrayList<>();
    
}