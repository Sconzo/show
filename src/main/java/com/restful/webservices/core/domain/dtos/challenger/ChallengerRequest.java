package com.restful.webservices.core.domain.dtos.challenger;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ChallengerRequest  implements Serializable {

    private static final long serialVersionUID = 3894811677492697539L;

    @NotNull
    private String name;
    @NotNull
    private Long score;
    @NotNull
    private Long sessionId;
    @NotNull
    private Long cardsLeft;
    @NotNull
    private Long studentsHelpLeft;
    @NotNull
    private Long skipsLeft;
    @NotNull
    private Long audienceHelpLeft;
}
