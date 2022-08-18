package com.restful.webservices.core.mappers;

import com.restful.webservices.core.domain.dtos.question.QuestionRequest;
import com.restful.webservices.core.domain.entities.OptionEntity;
import com.restful.webservices.core.domain.entities.QuestionEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OptionMapper {

    public static OptionEntity requestToEntity(QuestionRequest questionRequest, QuestionEntity questionEntity) {
        OptionEntity optionEntity = new OptionEntity();
        if(Objects.equals(questionRequest.getAnswersDescription().size(),4)) {
            optionEntity.setOption1(questionRequest.getAnswersDescription().get(0));
            optionEntity.setOption2(questionRequest.getAnswersDescription().get(1));
            optionEntity.setOption3(questionRequest.getAnswersDescription().get(2));
            optionEntity.setOption4(questionRequest.getAnswersDescription().get(3));
        }
        else if(Objects.equals(questionRequest.getAnswersDescription().size(),2)){
            optionEntity.setOption1(questionRequest.getAnswersDescription().get(0));
            optionEntity.setOption2(questionRequest.getAnswersDescription().get(1));
        }
        else{
            //throw error
        }
        optionEntity.setQuestion(questionEntity);
        return optionEntity;
    }
}
