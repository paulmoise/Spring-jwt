package com.pmtech.entifribackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class InformationDto {

    private String title;
    private String object;
    private Date datPost;
    private String ref;
    private Date datEven;
    private String description;
    private String periode;
    private String fileName;

    private int destinaire;


    private Long admin_id;
    private Long[] niveauEtudes = new Long[3] ;
    private String[] specialites= new String[5] ;
    private Long[] etudiants = new Long[4];
    private Long[] enseignants = new Long[4];
    private String typInfo;
   // private MultipartFile file;



}

