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
        if(Objects.equals(questionRequest.getOptions().size(),4)) {
            optionEntity.setOption1(questionRequest.getOptions().get(0).getOptionText());
            optionEntity.setOption2(questionRequest.getOptions().get(1).getOptionText());
            optionEntity.setOption3(questionRequest.getOptions().get(2).getOptionText());
            optionEntity.setOption4(questionRequest.getOptions().get(3).getOptionText());
        }
        optionEntity.setQuestion(questionEntity);
        return optionEntity;
    }
}
