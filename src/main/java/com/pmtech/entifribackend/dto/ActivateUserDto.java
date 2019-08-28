package com.pmtech.entifribackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ActivateUserDto {

    private String matricule;
    private String defaultPassword;
    private String password;
    private String confirmPassword;

}
