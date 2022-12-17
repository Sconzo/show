package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.LongAndStringDTO;
import com.restful.webservices.core.domain.dtos.question.OptionObject;
import com.restful.webservices.core.domain.dtos.question.QuestionRequest;
import com.restful.webservices.core.domain.dtos.question.QuestionResponse;
import com.restful.webservices.core.domain.entities.OptionEntity;
import com.restful.webservices.core.domain.entities.QuestionEntity;
import com.restful.webservices.core.domain.entities.SessionEntity;
import com.restful.webservices.core.domain.enums.LevelEnum;
import com.restful.webservices.core.domain.enums.TypeEnum;
import com.restful.webservices.core.persistence.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return questionResponse;
    }

    public static void setOptions(QuestionResponse questionResponse, OptionEntity optionEntity) {
        List<LongAndStringDTO> list = new ArrayList<>();
        LongAndStringDTO dto1 = LongAndStringDTO.builder()
                .id(1L)
                .description(optionEntity.getOption1())
                .build();
        list.add(dto1);
        LongAndStringDTO dto2 = LongAndStringDTO.builder()
                .id(2L)
                .description(optionEntity.getOption2())
                .build();
        list.add(dto2);
        LongAndStringDTO dto3 = LongAndStringDTO.builder()
                .id(3L)
                .description(optionEntity.getOption3())
                .build();
        list.add(dto3);
        LongAndStringDTO dto4 = LongAndStringDTO.builder()
                .id(4L)
                .description(optionEntity.getOption4())
                .build();
        list.add(dto4);

        questionResponse.setOptions(list);
    }
}
