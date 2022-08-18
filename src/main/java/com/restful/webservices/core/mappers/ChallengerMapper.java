package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.challenger.ChallengerRequest;
import com.restful.webservices.core.domain.entities.ChallengerEntity;
import com.restful.webservices.core.domain.entities.SessionEntity;
import org.springframework.stereotype.Component;

@Component
public class ChallengerMapper {

    public static ChallengerEntity requestToEntity(ChallengerRequest challengerRequest, SessionEntity sessionEntity){

        ChallengerEntity challengerEntity = ChallengerEntity.builder().challengerName(challengerRequest.getName())
                .session(sessionEntity).score(challengerRequest.getScore()).cardsLeft(challengerRequest.getCardsLeft())
                .studentsHelpLeft(challengerRequest.getStudentsHelpLeft()).skipsLeft(challengerRequest.getSkipsLeft())
                .audienceHelpLeft(challengerRequest.getAudienceHelpLeft()).build();


        return challengerEntity;
    }
}
