package com.restful.webservices.core.persistence.repositories;

import com.restful.webservices.core.domain.dtos.session.SessionResponse;
import com.restful.webservices.core.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
    @Query(value = "SELECT new com.restful.webservices.core.domain.dtos.session.SessionResponse( " +
            "ts.sessionName, ts.numberOfQuestions, ts.numberOfGroups, ts.numberOfChallengers, ts.cards, ts.students, " +
            "ts.skips, ts.audienceHelp, ts.id, ts.createdIn) " +
            "FROM SessionEntity ts " +
            "ORDER BY ts.createdIn DESC")
    List<SessionResponse> findAllAndSortByCreationDate();

    @Query(value = "SELECT ts from SessionEntity ts " +
            "WHERE ts.id in :ids")
    List<SessionEntity> findAllPossibleSessions(List<Long> ids);

    @Query(value = "SELECT COUNT(tq) from QuestionEntity tq " +
            "LEFT JOIN SessionEntity ts on ts.id = tq.session.id " +
            "WHERE ts.id = :id")
    Long getNumberOfQuestionsCreated(Long id);
}
