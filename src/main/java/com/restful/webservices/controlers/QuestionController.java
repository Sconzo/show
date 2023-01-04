package com.restful.webservices.controlers;

import com.restful.webservices.core.domain.dtos.question.QuestionRequest;
import com.restful.webservices.core.domain.dtos.question.QuestionResponse;
import com.restful.webservices.core.services.QuestionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "")
    public QuestionResponse getQuestion(
            @RequestParam Long id,
            HttpServletResponse servletResponse) {

        QuestionResponse questionResponse = questionService.getQuestion(id);

        return questionResponse;
    }

    @GetMapping(path = "/{sessionId}")
    public List<QuestionResponse> getQuestionsForChallenger(
            @PathVariable Long sessionId,
            HttpServletResponse servletResponse) {

        List<QuestionResponse> questionResponse = questionService.getQuestionsForChallenger(sessionId);

        return questionResponse;
    }
    @PostMapping(
            path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE )
    public QuestionResponse createQuestion(
            @RequestBody @Valid List<QuestionRequest> questionRequest,
            HttpServletResponse servletResponse) {

        questionService.createQuestion(questionRequest);

        return new QuestionResponse();
    }

    @GetMapping(path = "/check")
    public Boolean checkAnswer(
            @RequestParam Long choiceId,
            @RequestParam Long questionId,
            HttpServletResponse servletResponse) {

        Boolean response = questionService.checkAnswer(choiceId, questionId);

        return response;
    }
}
