package com.fr.yoni.registrant.controllers;

import com.fr.yoni.registrant.domain.Registrant;
import com.fr.yoni.registrant.repositories.RegistrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class RegistrantController {

    @Autowired
    RegistrantRepository registrantRepository;

    @GetMapping(value = "/registrants")
    public List<Registrant> list() {
        return registrantRepository.findAll();
    }

    @GetMapping(value = "/registrants/{id}")
    public Optional<Registrant> get(@PathVariable Long id) {
        Optional<Registrant> registrantInstance = registrantRepository.findById(id);

        if (!registrantInstance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified registrant doesn't exist");

        return registrantInstance;
    }

    @PostMapping(value = "/registrants")
    Registrant createNewRegistrant(@RequestBody Registrant registrant){
        String country = registrant.getCountry().toUpperCase();
        registrant.setCountry(country);
        if(registrant.getAge()>17 && registrant.getCountry().equals("FRANCE")){
            return registrantRepository.save(registrant);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't create registrat under 18 years old");
    }

}

