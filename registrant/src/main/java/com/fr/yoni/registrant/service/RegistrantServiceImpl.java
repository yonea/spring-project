package com.fr.yoni.registrant.service;

import com.fr.yoni.registrant.domain.Registrant;
import com.fr.yoni.registrant.repositories.RegistrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrantServiceImpl implements RegistrantService {

    @Autowired
    RegistrantRepository registrantRepository;

    @Override
    public void createRegistrant(Registrant registrant){
        if(checkRegistrant(registrant)){
            registrantRepository.save(registrant);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't create registrant under 18 years old");
        }
    }

    @Override
    public Optional<Registrant> getOneRegistrant(Long id) {
        Optional<Registrant> registrantInstance = registrantRepository.findById(id);

        if (!registrantInstance.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified registrant doesn't exist");

        return registrantInstance;
    }

    @Override
    public List<Registrant> getAllRegistrant() {
        return registrantRepository.findAll();
    }

    /**
     * Check if registred user is adult (>18 years) and that live in France
     * @param registrant
     * @return boolean
     */
    public boolean checkRegistrant(Registrant registrant) {
        return registrant.getAge() > 17 && registrant.getCountry().equals("FRANCE");
    }
}
