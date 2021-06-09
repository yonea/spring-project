package com.fr.yoni.registrant.repository;

import com.fr.yoni.registrant.domain.Registrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RegistrantRepository interface using JpaRepository to manage data
 * @author Yoni Baroukh
 */
@Repository
public interface RegistrantRepository extends JpaRepository<Registrant, Long> {
}
