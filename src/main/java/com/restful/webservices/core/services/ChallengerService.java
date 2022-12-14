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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ChallengerResponse createChallenger(List<ChallengerRequest> challengerRequest) {
        ChallengerResponse challengerResponse = new ChallengerResponse();
        List<ChallengerEntity> challengerEntityList = new ArrayList<>();
        List<SessionEntity> sessionEntityList = sessionRepository.findAllPossibleSessions(
                challengerRequest.stream().map(ChallengerRequest::getSessionId).collect(Collectors.toList()));

        challengerEntityList = ChallengerMapper.requestToEntity(challengerRequest, sessionEntityList);


        challengerRepository.saveAll(challengerEntityList);

        return challengerResponse;
    }
}
