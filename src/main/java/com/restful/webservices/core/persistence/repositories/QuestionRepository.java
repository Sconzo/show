package com.restful.webservices.core.persistence.repositories;

import com.restful.webservices.core.domain.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query(value = "SELECT tq from QuestionEntity tq " +
            "WHERE tq.session.id = :sessionId")
    List<QuestionEntity> findAllBySessionId(Long sessionId);
}
