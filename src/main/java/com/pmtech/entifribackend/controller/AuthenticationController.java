package com.pmtech.entifribackend.controller;

import com.pmtech.entifribackend.dto.LoginFormDto;
import com.pmtech.entifribackend.dto.UserDto;
import com.pmtech.entifribackend.entities.AppUser;
import com.pmtech.entifribackend.exception.UnauthorizedException;
import com.pmtech.entifribackend.security.JwtTokenUtil;
import com.pmtech.entifribackend.security.JwtUser;
import com.pmtech.entifribackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;


    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private AccountService accountService;


    @PostMapping("/simpleLogin")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginFormDto dto, HttpServletRequest request, HttpServletResponse response){
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getMatricule(),
                    dto.getPassword()));
            final JwtUser userDetails = (JwtUser)authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(userDetails);
            response.setHeader(tokenHeader, token);
            return new ResponseEntity<>(new UserDto(userDetails.getUser(), token), HttpStatus.OK);

        }catch (Exception e){
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<AppUser> getUser(Principal principal){
        AppUser user = accountService.loadUserByUserName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
