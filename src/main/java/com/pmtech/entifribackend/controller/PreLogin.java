package com.pmtech.entifribackend.controller;

import com.pmtech.entifribackend.dto.ActivateUserDto;
import com.pmtech.entifribackend.entities.AppUser;
import com.pmtech.entifribackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;

@RestController
public class PreLogin {

    @Autowired private AccountService accountService;

    @GetMapping("/isUserRegistrated")
    public ResponseEntity<AppUser> activateUserCompte(@RequestParam("matricule") String matricule){
        AppUser user = accountService.loadUserByUserName(matricule);
        if (user ==null){
            throw new UsernameNotFoundException("User not found");
        }else{
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PutMapping("/activationRequest")
    public ResponseEntity<Void> sendActivationRequest(@RequestParam("matricule")String matricule) throws AddressException {
       AppUser user = accountService.loadUserByUserName(matricule);
       if (!user.isActive()){
           boolean success = accountService.sendActivationRequest(matricule);
           if (success) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
       }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/activation")
    public ResponseEntity<AppUser> activateUserCompte(@RequestBody ActivateUserDto dto){
        AppUser user =accountService.activateCompte(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
