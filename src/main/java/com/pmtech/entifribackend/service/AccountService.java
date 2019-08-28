package com.pmtech.entifribackend.service;

import com.pmtech.entifribackend.dto.ActivateUserDto;
import com.pmtech.entifribackend.entities.AppRole;
import com.pmtech.entifribackend.entities.AppUser;

import javax.mail.internet.AddressException;

public interface AccountService  {

    public AppUser activateCompte(String username, String password, String confirmedPassword, String defaultPassword);
    public String getEmailByUsername(String username);
    public AppUser saveUser(String username, String password, String confirmedPassword, String defaultPassword);
    public AppUser loadUserByUserName(String username);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String role, String username);
    public void generateUserDefaultPassword(String username);
    public String generateAutoPwd(int length);
    public AppUser activateCompte(ActivateUserDto dto);
    public boolean sendActivationRequest(String matricule) throws AddressException;

}
