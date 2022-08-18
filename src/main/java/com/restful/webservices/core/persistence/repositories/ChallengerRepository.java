package com.restful.webservices.core.persistence.repositories;

import com.restful.webservices.core.domain.entities.ChallengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengerRepository extends JpaRepository<ChallengerEntity,Long> {

}
