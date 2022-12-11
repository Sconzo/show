package com.restful.webservices.core.domain.dtos.session;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {
    private static final long serialVersionUID = -1032241830132959255L;

    @NotNull
    private String sessionName;

    @NotNull
    private Long questionsPerChallenger;

    @NotNull
    private Long numberOfGroups;

    @NotNull
    private Long numberOfChallengers;

    @NotNull
    private Boolean cards;

    @NotNull
    private Boolean studentsHelp;

    @NotNull
    private Boolean skips;

    @NotNull
    private Boolean audienceHelp;

}
