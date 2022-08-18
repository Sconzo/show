package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.session.SessionRequest;
import com.restful.webservices.core.domain.dtos.session.SessionResponse;
import com.restful.webservices.core.domain.entities.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionMapper {

    public static SessionEntity requestToEntity(SessionRequest sessionRequest){

        return SessionEntity.builder().sessionName(sessionRequest.getSessionName())
                .numberOfQuestions(sessionRequest.getNumberOfQuestions()).numberOfChallengers(sessionRequest.getNumberOfChallengers())
                .numberOfGroups(sessionRequest.getNumberOfGroups()).cards(sessionRequest.getCardsLeft()).skips(sessionRequest.getSkipsLeft())
                .students(sessionRequest.getStudentsLeft()).audienceHelp(sessionRequest.getAudienceHelpLeft()).build();
    }

    public static SessionResponse entityToResponse(SessionEntity sessionEntity) {
        return SessionResponse.builder().sessionId(sessionEntity.getId()).sessionName(sessionEntity.getSessionName())
                .numberOfQuestions(sessionEntity.getNumberOfQuestions()).numberOfGroups(sessionEntity.getNumberOfGroups())
                .build();
    }
}