package com.pmtech.entifribackend.service;

import com.pmtech.entifribackend.dto.ActivateUserDto;
import com.pmtech.entifribackend.entities.AppRole;
import com.pmtech.entifribackend.entities.AppUser;
import com.pmtech.entifribackend.repository.AppRoleRepository;
import com.pmtech.entifribackend.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import java.util.Date;
import java.util.Random;


@Service
@Transactional // the app will make a commit after call of method in this service class
public class AccountServiceImpl implements  AccountService {



    private final String emailObject="Activation de votre compte depuis l'ENT IFRI";


    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SendEmailService sendEmailService;


    public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,SendEmailService sendEmailService) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendEmailService=sendEmailService;
    }

    //username is equal to user's matricule and defaultPassword is generated automatically
    @Override
    public AppUser activateCompte(String username, String password, String confirmedPassword, String defaultPassword) {

        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser != null){
            if (defaultPassword.equals(appUser.getDefaultPassword())){
                if (password.equals(confirmedPassword)){
                    appUser.setPassword(bCryptPasswordEncoder.encode(password));
                    appUser.setActivationDate(new Date());
                    appUser.setActive(true);
                }else throw new RuntimeException("please confirm your password");
            }else throw new RuntimeException("please check your email to put the code that you receive");
        }else throw new RuntimeException("Your are not registred in system check with administrator");

        return appUserRepository.save(appUser);
    }

    @Override
    public String getEmailByUsername(String username) {
        return appUserRepository.findByUsername(username).getEmail();
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword, String defaultPassword) {
        return null;
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String role, String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(role);
        appUser.getRoles().add(appRole);
    }

    @Override
    public void generateUserDefaultPassword(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        String defaultPassword = generateAutoPwd(10);
        user.setDefaultPassword(bCryptPasswordEncoder.encode(defaultPassword));
    }

    /*public boolean sendDefaultPasswordToUserByEmail(String username){

    }*/

    public String generateAutoPwd(int length){
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String values = Capital_chars + Small_chars + numbers;

        Random random_method = new Random();

        char[] pwd = new char[length];

        for (int i=0; i<length; i++){
            pwd[i]= values.charAt(random_method.nextInt(values.length()));
        }
        return String.valueOf(pwd) ;
    }

    @Override
    public AppUser activateCompte(ActivateUserDto dto) {
            AppUser appUser = appUserRepository.findByUsername(dto.getMatricule());
            if (appUser != null){
                if (bCryptPasswordEncoder.matches(dto.getDefaultPassword(),appUser.getDefaultPassword())){
                    if (dto.getPassword().equals(dto.getConfirmPassword())){
                        appUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
                        appUser.setActivationDate(new Date());
                        appUser.setActive(true);
                    }else throw new RuntimeException("please confirm your password");
                }else throw new RuntimeException("please check your email to put the code that you receive");
            }else throw new RuntimeException("Your are not registred in system check with administrator");

            return appUserRepository.save(appUser);
    }



    @Override
    public boolean sendActivationRequest(String matricule) throws AddressException {
        AppUser user = appUserRepository.findByUsername(matricule);
        if (user != null){
            String defaultPassword = generateAutoPwd(10);
            user.setDefaultPassword(bCryptPasswordEncoder.encode(defaultPassword));
            String userEmail = user.getEmail();
            sendEmailService.sendingEmail(emailObject, "Votre mode de passe par defaut est : "+defaultPassword+" .Vous devez modifier ce mot de passe lors de votre premiere connexion",
                    userEmail);
            user.setDefaultPassword(bCryptPasswordEncoder.encode(defaultPassword));
            appUserRepository.save(user);
            return  true;
        }else return false;
    }
}
