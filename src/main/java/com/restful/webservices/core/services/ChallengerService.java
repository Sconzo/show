package com.restful.webservices.core.services;

import com.restful.webservices.core.domain.dtos.challenger.ChallengerRequest;
import com.restful.webservices.core.domain.dtos.challenger.ChallengerResponse;
import com.restful.webservices.core.domain.entities.ChallengerEntity;
import com.restful.webservices.core.domain.entities.SessionEntity;
import com.restful.webservices.core.mappers.ChallengerMapper;
import com.restful.webservices.core.mappers.QuestionMapper;
import com.restful.webservices.core.persistence.repositories.ChallengerRepository;
import com.restful.webservices.core.persistence.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ChallengerService {

    private final ChallengerMapper challengerMapper;
    private final ChallengerRepository challengerRepository;
    private final SessionRepository sessionRepository;

    public ChallengerService(ChallengerMapper challengerMapper, ChallengerRepository challengerRepository, SessionRepository sessionRepository) {
        this.challengerMapper = challengerMapper;
        this.challengerRepository = challengerRepository;
        this.sessionRepository = sessionRepository;
    }

    public ChallengerResponse createChallenger(ChallengerRequest challengerRequest) {
        ChallengerResponse challengerResponse = new ChallengerResponse();
        ChallengerEntity challengerEntity = new ChallengerEntity();
        Optional<SessionEntity> sessionEntity = sessionRepository.findById(challengerRequest.getSessionId());

        if(sessionEntity.isPresent()){
            challengerEntity = ChallengerMapper.requestToEntity(challengerRequest, sessionEntity.get());
        }

        challengerRepository.save(challengerEntity);

        return challengerResponse;
    }
}
