package com.fr.yoni.registrant;

import com.fr.yoni.registrant.service.RegistrantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Yoni Baroukh
 */
@SpringBootApplication
public class RegistrantApplication {

    @Autowired
    RegistrantServiceImpl registrantService;

    public static void main(String[] args) {
        SpringApplication.run(RegistrantApplication.class, args);
    }

}
