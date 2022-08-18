package com.restful.webservices.core.domain.dtos.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class QuestionResponse implements Serializable {

    private static final long serialVersionUID = -8558345065783499502L;

    private Long questionId;
    private String questionDescription;
    private String sessionName;
    private Long sessionId;
    private List<String> options;
    private String type;
    private String level;
}
