package com.restful.webservices.core.persistence.repositories;

import com.restful.webservices.core.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
}
