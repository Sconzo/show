package com.restful.webservices.core.services;

import com.restful.webservices.core.domain.dtos.question.QuestionRequest;
import com.restful.webservices.core.domain.dtos.question.QuestionResponse;
import com.restful.webservices.core.domain.entities.OptionEntity;
import com.restful.webservices.core.domain.entities.QuestionEntity;
import com.restful.webservices.core.domain.enums.LevelEnum;
import com.restful.webservices.core.domain.enums.TypeEnum;
import com.restful.webservices.core.mappers.OptionMapper;
import com.restful.webservices.core.mappers.QuestionMapper;
import com.restful.webservices.core.persistence.repositories.OptionRepository;
import com.restful.webservices.core.persistence.repositories.QuestionRepository;
import com.restful.webservices.core.persistence.repositories.SessionRepository;
import com.restful.webservices.exception.notfound.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SessionRepository sessionRepository;
    private final OptionRepository optionRepository;


    public QuestionService(QuestionRepository questionRepository, SessionRepository sessionRepository, OptionRepository optionRepository) {

        this.questionRepository = questionRepository;
        this.sessionRepository = sessionRepository;
        this.optionRepository = optionRepository;
    }

    public QuestionResponse createQuestion(List<QuestionRequest> questionRequestList) {
        QuestionEntity questionEntity;

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
        QuestionResponse questionResponse;

        QuestionEntity questionEntity = questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);

        questionResponse = getQuestionResponse(questionEntity);

        return questionResponse;
    }

    private QuestionResponse getQuestionResponse(QuestionEntity questionEntity) {
        QuestionResponse questionResponse;
        questionResponse = QuestionMapper.entityToResponse(questionEntity);
        if(questionEntity.getType().equals(TypeEnum.MULTIPLE_CHOICE)){
            OptionEntity optionEntity = optionRepository.findAllByQuestionId(questionEntity.getId());
            QuestionMapper.setOptions(questionResponse,optionEntity);
        }
        return questionResponse;
    }

    public Boolean checkAnswer(Long choiceId,Long questionId) {

        Optional<QuestionEntity> questionEntity = questionRepository.findById(questionId);

        if(questionEntity.isPresent()){
            QuestionEntity question = questionEntity.get();
            if(Objects.equals(question.getType(), TypeEnum.MULTIPLE_CHOICE)){
                return checkChoice(Objects.equals(question.getMultipleChoiceAnswer(), choiceId));
            }
            else if (Objects.equals(question.getType(), TypeEnum.TRUE_OR_FALSE)) {
                Boolean choiceBoolean = getBooleanChoice(choiceId);

                return checkChoice(Objects.equals(question.getTrueOrFalseAnswer(), choiceBoolean));
            }
            else{
                //throw error
            }
        }
        return null;
    }


    public List<QuestionResponse> getQuestionsForChallenger(Long sessionId) {
        List<QuestionResponse> responseList = new ArrayList<>();
        List<QuestionResponse> easyList = new ArrayList<>();
        List<QuestionResponse> intermediateList = new ArrayList<>();
        List<QuestionResponse> hardList = new ArrayList<>();
        List<QuestionEntity> entityList = questionRepository.findAllBySessionId(sessionId);
        Collections.shuffle(entityList);
        for(QuestionEntity questionEntity : entityList){
            QuestionResponse questionResponse = getQuestionResponse(questionEntity);
            if(LevelEnum.EASY.name().equals(questionResponse.getLevel())){
                easyList.add((questionResponse));
            }
            else if(LevelEnum.INTERMEDIATE.name().equals(questionResponse.getLevel())){
                intermediateList.add((questionResponse));
            }
            else if(LevelEnum.HARD.name().equals(questionResponse.getLevel())){
                hardList.add((questionResponse));
            }
        }

        if(!entityList.isEmpty()){
            int numberOfChallengers = entityList.get(0).getSession().getNumberOfChallengers().intValue();

            int partitionSizeEasy = Math.max(easyList.size()/numberOfChallengers, 1);
            List<List<QuestionResponse>> partitionsEasy = new ArrayList<>();
            for (int i=0; i<easyList.size(); i += partitionSizeEasy) {
                partitionsEasy.add(easyList.subList(i, Math.min(i + partitionSizeEasy, easyList.size())));
            }
            int partitionSizeIntermediate = Math.max(intermediateList.size()/numberOfChallengers,1);
            List<List<QuestionResponse>> partitionsIntermediate = new ArrayList<>();
            for (int i=0; i<intermediateList.size(); i += partitionSizeIntermediate) {
                partitionsIntermediate.add(intermediateList.subList(i, Math.min(i + partitionSizeIntermediate, intermediateList.size())));
            }
            int partitionSizeHard = Math.max(hardList.size()/numberOfChallengers,1);
            List<List<QuestionResponse>> partitionsHard = new ArrayList<>();
            for (int i=0; i<hardList.size(); i += partitionSizeHard) {
                partitionsHard.add(hardList.subList(i, Math.min(i + partitionSizeHard, hardList.size())));
            }

            int max = Math.max(partitionsEasy.size(), Math.max(partitionsIntermediate.size(), partitionsHard.size()));
            for(int k=0;k<max;k++){
                if(partitionsEasy.size() > k){
                    responseList.addAll(partitionsEasy.get(k));
                }
                if(partitionsIntermediate.size() > k){
                    responseList.addAll(partitionsIntermediate.get(k));
                }
                if(partitionsHard.size() > k){
                    responseList.addAll(partitionsHard.get(k));
                }
            }
        }

        return responseList;
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
