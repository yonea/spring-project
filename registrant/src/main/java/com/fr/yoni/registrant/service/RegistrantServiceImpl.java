package com.fr.yoni.registrant.service;

import com.fr.yoni.registrant.domain.Registrant;
import com.fr.yoni.registrant.repositories.RegistrantRepository;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Yoni Baroukh
 */
@Service
public class RegistrantServiceImpl implements RegistrantService {

    @Autowired
    RegistrantRepository registrantRepository;

    @Override
    public void createRegistrant(Registrant registrant){
        if(mandatoryFieldsAreNull(registrant)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "At least one mandatory fields (lastname, firstname, email, age, country) not filled in");
        }

        if (checkAgeRegistrant(registrant) && checkCountryRegistrant(registrant)) {
            registrantRepository.save(registrant);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Can't create registrant under 18 years old and that doesn't live in France");
        }
    }

    @Override
    public Optional<Registrant> getOneRegistrant(Long id) {
        Optional<Registrant> registrantInstance = registrantRepository.findById(id);

        if (!registrantInstance.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified registrant doesn't exist");
        }

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
    private boolean checkAgeRegistrant(Registrant registrant) {
        return registrant.getAge() > 17;
    }
    private boolean checkCountryRegistrant(Registrant registrant) {
        return registrant.getCountry().equals("FRANCE");
    }

    private boolean mandatoryFieldsAreNull(Registrant registrant){
        return registrant.getFirstname() == null ||
                registrant.getLastname() == null ||
                registrant.getEmail() == null ||
                registrant.getAge() == null ||
                registrant.getCountry() == null;
    }
}
