package com.restful.webservices.exception;

import com.restful.webservices.core.commons.DomainReturnCode;
import com.restful.webservices.exception.notfound.QuestionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuestionNotFoundException(QuestionNotFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder().error(DomainReturnCode.QUESTION_NOT_FOUND.toString())
                .description(DomainReturnCode.QUESTION_NOT_FOUND.toString()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
