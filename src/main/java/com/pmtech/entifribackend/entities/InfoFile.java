package com.pmtech.entifribackend.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * InfoFile
 */
@Entity @Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class InfoFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String modifiedFileName;
    private String extension;
    private String description;
    private Date datPost;

    /* @OneToOne
     @JoinTable(name = "info_Id")
     private ResultExam resultExam;*/

     @OneToOne
     @JoinTable(name = "info_Id_eDtps")
     private EmploiDuTemps emploiDuTemps;
    

    //private EmploiDuTemps eDuTemps;
}