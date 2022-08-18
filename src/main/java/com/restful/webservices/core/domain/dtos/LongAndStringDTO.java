package com.restful.webservices.core.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LongAndStringDTO implements Serializable {
    private static final long serialVersionUID = -4978338171425788645L;

    private Long id;
    private String description;

}
