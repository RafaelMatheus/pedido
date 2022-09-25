package br.com.lunch.calculator.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<Object> processExceptions(final Exception ex, final WebRequest request) {
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(buildErrorResponse(IssueHandler.createIssue(ex)));
//    }
}
