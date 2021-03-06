package com.fr.yoni.registrant.controller;

import com.fr.yoni.registrant.domain.Registrant;
import com.fr.yoni.registrant.service.RegistrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RegistrantController class with 3 services :
 * - register a user POST /registrants
 * - displays the details of a registered user GET /registrants/{id}
 * - displays the details of all registered users GET /registrants
 * @see RegistrantService
 * @author Yoni Baroukh
 */
@Validated
@RestController
public class RegistrantController {

    @Autowired
    RegistrantService registrantService;

    @GetMapping(value = "/registrants")
    public ResponseEntity<Object> list() {
        return new ResponseEntity<>(registrantService.getAllRegistrant(), HttpStatus.OK);
    }

    @GetMapping(value = "/registrants/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return new ResponseEntity<>(registrantService.getOneRegistrant(id), HttpStatus.OK);
    }

    @PostMapping(value = "/registrants")
    ResponseEntity<Object> createNewRegistrant(@RequestBody Registrant registrant) {
        String country = registrant.getCountry().toUpperCase();
        registrant.setCountry(country);
        registrantService.createRegistrant(registrant);
        return new ResponseEntity<>("Registrant is created successfully", HttpStatus.CREATED);
    }

}

