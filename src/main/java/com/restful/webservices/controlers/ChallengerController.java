package com.restful.webservices.controlers;

import com.restful.webservices.core.domain.dtos.challenger.ChallengerRequest;
import com.restful.webservices.core.domain.dtos.challenger.ChallengerResponse;
import com.restful.webservices.core.services.ChallengerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/challenger")
public class ChallengerController {

    private final ChallengerService challengerService;

    public ChallengerController(ChallengerService challengerService) {

        this.challengerService = challengerService;
    }

    @GetMapping(path = "")
    public ChallengerResponse getSession(
            @RequestParam Long id,
            HttpServletResponse servletResponse) {

        return new ChallengerResponse();
    }

    @PostMapping(path = "")
    public ChallengerResponse createSession(
            @RequestBody @Valid List<ChallengerRequest> questionRequest,
            HttpServletResponse servletResponse) {

        ChallengerResponse challengerResponse = challengerService.createChallenger(questionRequest);
        return new ChallengerResponse();
    }

}
