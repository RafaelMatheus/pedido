package br.com.lunch.calculator.exception.handler;

import br.com.lunch.calculator.exception.NotFoundException;
import br.com.lunch.calculator.exception.response.FieldMessage;
import br.com.lunch.calculator.exception.response.StandardError;
import br.com.lunch.calculator.exception.response.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> erroInterno(Exception e, HttpServletRequest request) {
        log.error("Erro interno", e);
        StandardError err = StandardError.builder()
                .error("Erro interno.")
                .path(request.getRequestURI())
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> saldoInsuficiente(NotFoundException e, HttpServletRequest request) {
        log.error("Objeto não encontrado", e);
        StandardError err = StandardError.builder()
                .error("Objecto não encontrado")
                .path(request.getRequestURI())
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest) {

        log.error("Erro na validação dos campos", methodArgumentNotValidException);
        ValidationError validationError = ValidationError.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro de validação")
                .path(httpServletRequest.getRequestURI()).build();


        validationError.setFieldMessages(methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> FieldMessage.builder().fieldName(x.getField()).message(x.getDefaultMessage()).build())
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);

    }
}
