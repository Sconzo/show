package com.restful.webservices.core.services;

import com.restful.webservices.core.domain.dtos.question.QuestionRequest;
import com.restful.webservices.core.domain.dtos.question.QuestionResponse;
import com.restful.webservices.core.domain.entities.OptionEntity;
import com.restful.webservices.core.domain.entities.QuestionEntity;
import com.restful.webservices.core.domain.entities.SessionEntity;
import com.restful.webservices.core.domain.enums.TypeEnum;
import com.restful.webservices.core.mappers.OptionMapper;
import com.restful.webservices.core.mappers.QuestionMapper;
import com.restful.webservices.core.persistence.repositories.OptionRepository;
import com.restful.webservices.core.persistence.repositories.QuestionRepository;
import com.restful.webservices.core.persistence.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final SessionRepository sessionRepository;
    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;


    public QuestionService(QuestionMapper questionMapper, QuestionRepository questionRepository, SessionRepository sessionRepository, OptionRepository optionRepository, OptionMapper optionMapper) {
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
        this.sessionRepository = sessionRepository;
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
    }

    public QuestionResponse createQuestion(List<QuestionRequest> questionRequestList) {
        QuestionEntity questionEntity = new QuestionEntity();

        for(QuestionRequest questionRequest : questionRequestList){
            questionEntity = QuestionMapper.requestToEntity(questionRequest, sessionRepository.findById(questionRequest.getSessionId()).get());
            questionRepository.save(questionEntity);
            if(Objects.equals(questionRequest.getType(),TypeEnum.MULTIPLE_CHOICE.name())) {
                OptionEntity optionEntity = OptionMapper.requestToEntity(questionRequest, questionEntity);
                optionRepository.save(optionEntity);
            }
        }
        return null;
    }

    public QuestionResponse getQuestion(Long id) {
        QuestionResponse questionResponse = new QuestionResponse();

        Optional<QuestionEntity> questionEntity = questionRepository.findById(id);

        if(questionEntity.isPresent()){
            questionResponse = QuestionMapper.entityToResponse(questionEntity.get());
        }
        return questionResponse;
    }

    public Boolean checkAnswer(Long choice,Long questionId) {

        Optional<QuestionEntity> questionEntity = questionRepository.findById(questionId);

        if(questionEntity.isPresent()){
            QuestionEntity question = questionEntity.get();
            if(Objects.equals(question.getType(), TypeEnum.MULTIPLE_CHOICE)){
                return checkChoice(Objects.equals(question.getMultipleChoiceAnswer(), choice));
            }
            else if (Objects.equals(question.getType(), TypeEnum.TRUE_OR_FALSE)) {
                Boolean choiceBoolean = getBooleanChoice(choice);

                return checkChoice(Objects.equals(question.getTrueOrFalseAnswer(), choiceBoolean));
            }
            else{
                //throw error
            }
        }


        return null;
    }

    private Boolean getBooleanChoice(Long choice) {
        Boolean choiceBoolean = null;
        if(Objects.equals(choice,1L)){
            return true;
        }
        else if (Objects.equals(choice,0L)){
            return false;
        }
        else {
            //throw error
        }
        return null;
    }

    private Boolean checkChoice(boolean correct) {
        if(correct){
            return true;
        }
        else{
            return false;
        }
    }
}
