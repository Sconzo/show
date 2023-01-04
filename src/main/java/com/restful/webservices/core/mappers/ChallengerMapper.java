package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.challenger.ChallengerRequest;
import com.restful.webservices.core.domain.entities.ChallengerEntity;
import com.restful.webservices.core.domain.entities.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ChallengerMapper {

    public static List<ChallengerEntity> requestToEntity(List<ChallengerRequest> challengerRequestList, List<SessionEntity> sessionEntityList){
        List<ChallengerEntity> challengerEntityList = new ArrayList<>();

        for (ChallengerRequest challengerRequest: challengerRequestList) {
            SessionEntity sessionEntity = sessionEntityList.stream().filter(
                    session -> Objects.equals(session.getId(),challengerRequest.getSessionId()))
                    .findFirst().orElseThrow();
            ChallengerEntity entity = ChallengerEntity.builder()
                    .challengerName(challengerRequest.getName())
                    .session(sessionEntity)
                    .score(challengerRequest.getScore())
                    .cardsLeft(challengerRequest.getCardsLeft())
                    .studentsHelpLeft(challengerRequest.getStudentsHelpLeft())
                    .skipsLeft(challengerRequest.getSkipsLeft())
                    .audienceHelpLeft(challengerRequest.getAudienceHelpLeft()).build();
            challengerEntityList.add(entity);

        }
        return challengerEntityList;
    }
}
