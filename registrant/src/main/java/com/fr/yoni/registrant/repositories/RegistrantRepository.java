package com.fr.yoni.registrant.repositories;

import com.fr.yoni.registrant.domain.Registrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrantRepository extends JpaRepository<Registrant, Long> {
}
