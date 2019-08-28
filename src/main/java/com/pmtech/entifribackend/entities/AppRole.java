package com.pmtech.entifribackend.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data  @NoArgsConstructor @AllArgsConstructor @ToString
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    public AppRole(ROLE role){
     this.id = role.getId();
     this.roleName = role.getName();
    }
}
