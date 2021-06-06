package com.fr.yoni.registrant.service;

import com.fr.yoni.registrant.domain.Registrant;

import java.util.List;
import java.util.Optional;

public interface RegistrantService {

    /**
     * Register a user
     * @param registrant
     */
    void createRegistrant(Registrant registrant);

    /**
     * Displays the details of a registered user
     * @param id
     * @return Optional<Registrant>
     */
    Optional<Registrant> getOneRegistrant(Long id);

    /**
     * Displays the details of all registered users
     * @return List<Registrant>
     */
    List<Registrant> getAllRegistrant();

}
