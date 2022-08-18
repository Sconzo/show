package com.restful.webservices.core.services;

import com.restful.webservices.core.domain.dtos.session.SessionRequest;
import com.restful.webservices.core.domain.dtos.session.SessionResponse;
import com.restful.webservices.core.domain.entities.SessionEntity;
import com.restful.webservices.core.mappers.SessionMapper;
import com.restful.webservices.core.persistence.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    public SessionService(SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    public SessionResponse createSession(SessionRequest sessionRequest) {

        SessionEntity sessionEntity = SessionMapper.requestToEntity(sessionRequest);

        sessionRepository.save(sessionEntity);

        return null;
    }

    public SessionResponse getSession(Long id) {

        SessionResponse sessionResponse = new SessionResponse();
        Optional<SessionEntity> sessionEntity = sessionRepository.findById(id);

        if(sessionEntity.isPresent()){
            sessionResponse = SessionMapper.entityToResponse(sessionEntity.get());
        }

        return sessionResponse;
    }
}
