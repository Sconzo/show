package com.restful.webservices.core.domain.dtos.question;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class QuestionRequest implements Serializable {

    private static final long serialVersionUID = -1522863600328345178L;

    @NotNull
    private String questionDescription;

    @NotNull
    private String type;

    @NotNull
    private String level;

    private Long multipleChoiceAnswer;

    private Boolean trueOrFalseAnswer;

    @NotNull
    private List<String> answersDescription;

    @NotNull
    private Long sessionId;
}
