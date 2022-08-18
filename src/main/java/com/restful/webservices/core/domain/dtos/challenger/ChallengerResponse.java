package com.restful.webservices.core.domain.dtos.challenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChallengerResponse implements Serializable {

    private static final long serialVersionUID = 4984991265406725125L;

    private Long challengerId;
    private String challengerName;
    private Long sessionId;
    private String sessionName;
    private Long score;
    private Long cardsLeft;
    private Long studentsHelpLeft;
    private Long audienceHelpLeft;
    private Long skipsLeft;
}
