package com.pmtech.entifribackend.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fichier
 */
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Fichier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String modifiedFileName;
    private String extension;
    private String description;
    private Date datPost;
    private Date updateDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "codMat")
    private Matiere matiere;
}