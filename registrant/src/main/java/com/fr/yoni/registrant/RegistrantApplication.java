package com.fr.yoni.registrant;

import com.fr.yoni.registrant.domain.Registrant;
import com.fr.yoni.registrant.service.RegistrantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegistrantApplication {

    @Autowired
    RegistrantServiceImpl registrantService;

    public static void main(String[] args) {
        SpringApplication.run(RegistrantApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println(registrantService.getAllRegistrant());
    }

}
