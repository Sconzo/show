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

    private Long sessionId;
    private String sessionName;
    private Long numberOfQuestions;
    private Long numberOfGroups;
    private Date createdIn;
}
