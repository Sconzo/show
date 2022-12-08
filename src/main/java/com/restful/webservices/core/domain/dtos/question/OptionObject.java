package com.restful.webservices.core.domain.dtos.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class OptionObject implements Serializable {

    private static final long serialVersionUID = 7782545905049224420L;

    private Long optionNumber;
    private Boolean correctOption;
    private String optionText;
}
