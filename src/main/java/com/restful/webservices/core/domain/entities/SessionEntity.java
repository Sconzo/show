package com.restful.webservices.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private Boolean cards;

    @Column(name = "students_help",nullable = false)
    private Boolean students;

    @Column(name = "skips",nullable = false)
    private Boolean skips;

    @Column(name = "audience_help",nullable = false)
    private Boolean audienceHelp;

    @Column(name = "created_in", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdIn;
}
