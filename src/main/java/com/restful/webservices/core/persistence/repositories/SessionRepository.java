package com.restful.webservices.core.persistence.repositories;

import com.restful.webservices.core.domain.dtos.session.SessionResponse;
import com.restful.webservices.core.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
    @Query(value =
            "SELECT new com.restful.webservices.core.domain.dtos.session.SessionResponse( " +
            "ts.id, ts.sessionName, ts.numberOfQuestions, ts.numberOfGroups, ts.createdIn) " +
            "from SessionEntity ts " +
            "ORDER BY ts.createdIn DESC")
    List<SessionResponse> findAllAndSortByCreationDate();
}
