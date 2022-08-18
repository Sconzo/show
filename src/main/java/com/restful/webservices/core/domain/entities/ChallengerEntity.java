package com.restful.webservices.core.domain.entities;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_challenger")
public class ChallengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "challenger_name",nullable = false)
    private String challengerName;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    @Column(name = "score",nullable = false)
    private Long score;

    @Column(name = "cardsLeft",nullable = false)
    private Long cardsLeft;

    @Column(name = "studentsHelpLeft",nullable = false)
    private Long studentsHelpLeft;

    @Column(name = "skipsLeft",nullable = false)
    private Long skipsLeft;

    @Column(name = "audienceHelpLeft",nullable = false)
    private Long audienceHelpLeft;

}
