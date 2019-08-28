package com.pmtech.entifribackend.dto;

import com.pmtech.entifribackend.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto {

    private AppUser user;
    private String token;
}
