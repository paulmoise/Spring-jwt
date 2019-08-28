package com.pmtech.entifribackend;

import com.pmtech.entifribackend.entities.AppRole;
import com.pmtech.entifribackend.entities.Etudiant;
import com.pmtech.entifribackend.entities.ROLE;
import com.pmtech.entifribackend.repository.AppRoleRepository;
import com.pmtech.entifribackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
@ComponentScan(basePackages = {"com.pmtech","it.ozimov.springboot"})
public class DemoApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	@Bean
	public CorsFilter corsFilter(){
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config=new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}




	@Bean
	CommandLineRunner start(AccountService accountService, AppRoleRepository appRoleRepository){
		return args -> {
			//AppRole role = appRoleRepository.save(new AppRole(ROLE.STUDENT));
		  //  accountService.saveRole(new AppRole(ROLE.ADMIN));
          //  accountService.saveRole(new AppRole(ROLE.STUDENT));
          //  accountService.saveRole(new AppRole(ROLE.TEACHER));

			//accountService.addRoleToUser(ROLE.STUDENT.getName(), "10324556");
		};
	}
}
