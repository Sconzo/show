package com.restful.webservices.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "session_name",nullable = false)
    private String sessionName;

    @Column(name = "number_of_questions",nullable = false)
    private Long numberOfQuestions;

    @Column(name = "number_of_challengers",nullable = false)
    private Long numberOfChallengers;

    @Column(name = "number_of_groups",nullable = false)
    private Long numberOfGroups;

    @Column(name = "cards",nullable = false)
    private Long cards;

    @Column(name = "studentsHelp",nullable = false)
    private Long students;

    @Column(name = "skips",nullable = false)
    private Long skips;

    @Column(name = "audienceHelp",nullable = false)
    private Long audienceHelp;

}
