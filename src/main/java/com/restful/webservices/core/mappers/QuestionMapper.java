package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.question.OptionObject;
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
import java.util.Optional;

@Component
public class QuestionMapper {

    public static QuestionEntity requestToEntity(QuestionRequest request, SessionEntity session){
        Long multipleChoiceAnswer = null;
        Boolean trueOrFalseAnswer = null;
        if(Objects.equals(request.getType(),TypeEnum.MULTIPLE_CHOICE.name())){
            Optional<OptionObject> correct = request.getOptions().stream().filter(OptionObject::getCorrectOption).findFirst();
            if(correct.isPresent()){
                multipleChoiceAnswer = correct.get().getOptionNumber();
            }
        }
        else if(Objects.equals(request.getType(),TypeEnum.TRUE_OR_FALSE.name())){
            Optional<OptionObject> correct = request.getOptions().stream().filter(OptionObject::getCorrectOption).findFirst();
            if(correct.isPresent()){
                if(Objects.equals(correct.get().getOptionNumber(),1L)){
                    trueOrFalseAnswer = Boolean.TRUE;
                }
                else if(Objects.equals(correct.get().getOptionNumber(),2L)){
                    trueOrFalseAnswer = Boolean.FALSE;
                }
            }
        }
        else{

        }

        QuestionEntity questionEntity = QuestionEntity.builder()
                .questionDescription(request.getQuestionDescription())
                .type(TypeEnum.valueOf(request.getType()))
                .level(LevelEnum.valueOf(request.getLevel()))
                .multipleChoiceAnswer(multipleChoiceAnswer)
                .trueOrFalseAnswer(trueOrFalseAnswer)
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
