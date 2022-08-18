package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.question.QuestionRequest;
import com.restful.webservices.core.domain.dtos.question.QuestionResponse;
import com.restful.webservices.core.domain.entities.OptionEntity;
import com.restful.webservices.core.domain.entities.QuestionEntity;
import com.restful.webservices.core.domain.entities.SessionEntity;
import com.restful.webservices.core.domain.enums.LevelEnum;
import com.restful.webservices.core.domain.enums.TypeEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class QuestionMapper {

    public static QuestionEntity requestToEntity(QuestionRequest request, SessionEntity session){

        QuestionEntity questionEntity = QuestionEntity.builder().questionDescription(request.getQuestionDescription())
                .type(TypeEnum.valueOf(request.getType())).level(LevelEnum.valueOf(request.getLevel()))
                .multipleChoiceAnswer(request.getMultipleChoiceAnswer()).trueOrFalseAnswer(request.getTrueOrFalseAnswer())
                .session(session).build();




        return questionEntity;
    }

    public static QuestionResponse entityToResponse(QuestionEntity questionEntity) {
        QuestionResponse questionResponse = QuestionResponse.builder().questionId(questionEntity.getId())
                .questionDescription(questionEntity.getQuestionDescription()).sessionId(questionEntity.getSession().getId())
                .sessionName(questionEntity.getSession().getSessionName()).type(questionEntity.getType().name())
                .level(questionEntity.getLevel().name()).build();

        List<String> options = new ArrayList<>();
//        if(Objects.equals(questionEntity.getType(),TypeEnum.MULTIPLE_CHOICE)){
//            options.add(questionEntity.getOption1());
//            options.add(questionEntity.getOption2());
//            options.add(questionEntity.getOption3());
//            options.add(questionEntity.getOption4());
//        }
//        else if (Objects.equals(questionEntity.getType(),TypeEnum.TRUE_OR_FALSE)){
//            options.add(questionEntity.getOption1());
//            options.add(questionEntity.getOption2());
//        }
//        else{
//            //throw error
//        }
        questionResponse.setOptions(options);
        return questionResponse;
    }
}
