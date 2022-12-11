package com.restful.webservices.controlers;

import com.restful.webservices.core.domain.dtos.session.SessionRequest;
import com.restful.webservices.core.domain.dtos.session.SessionResponse;
import com.restful.webservices.core.services.SessionService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(path = "")
    public SessionResponse getSession(
            @RequestParam Long id,
            HttpServletResponse servletResponse) {

        SessionResponse sessionResponse = sessionService.getSession(id);

        return sessionResponse;
    }

    @PostMapping(path = "")
    public SessionResponse createSession(
            @RequestBody @Valid SessionRequest sessionRequest,
            HttpServletResponse servletResponse) {

        SessionResponse sessionResponse = sessionService.createSession(sessionRequest);

        return sessionResponse;
    }

    @GetMapping(path = "/all")
    public List<SessionResponse> getAllSessions(
            HttpServletResponse servletResponse) {

        List<SessionResponse> sessionResponse = sessionService.getAllSession();

        return sessionResponse;
    }
}
