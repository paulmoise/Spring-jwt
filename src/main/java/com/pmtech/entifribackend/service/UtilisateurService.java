package com.pmtech.entifribackend.service;

import com.pmtech.entifribackend.entities.AppUser;
import com.pmtech.entifribackend.repository.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UtilisateurService
 */
@Service
public class UtilisateurService {

    @Autowired private AppUserRepository uRepository;

    public AppUser save(AppUser u){
        return uRepository.save(u);
    }
    
}