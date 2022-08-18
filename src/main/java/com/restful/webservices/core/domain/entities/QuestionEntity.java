package com.restful.webservices.core.domain.entities;

import com.restful.webservices.core.domain.enums.LevelEnum;
import com.restful.webservices.core.domain.enums.TypeEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question_description",nullable = false)
    private String questionDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private TypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "level",nullable = false)
    private LevelEnum level;

    @Column(name = "multiple_choice_answer",nullable = true)
    private Long multipleChoiceAnswer;

    @Column(name = "true_or_false_answer",nullable = true)
    private Boolean trueOrFalseAnswer;

    @ManyToOne
    @JoinColumn(name = "session_id",nullable = false)
    private SessionEntity session;

}
