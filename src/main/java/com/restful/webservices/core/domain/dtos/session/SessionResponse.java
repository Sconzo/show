package com.restful.webservices.core.domain.dtos.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse implements Serializable {
    private static final long serialVersionUID = 7499532832605495159L;

    private String sessionName;
    private Long numberOfQuestions;
    private Long numberOfGroups;
    private Long numberOfChallengers;
    private Boolean cards;
    private Boolean studentsHelp;
    private Boolean skips;
    private Boolean audienceHelp;
    private Long sessionId;
    private Date createdIn;
}
