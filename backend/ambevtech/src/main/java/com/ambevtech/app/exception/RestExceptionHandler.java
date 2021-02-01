package com.ambevtech.app.exception;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<Object> handler(ServiceException exception) {

        if (ObjectUtils.isEmpty(exception.getViolations())) {
            JsonObject response = new JsonObject();
            response.addProperty("tipo", exception.getErrorException().getTipo());
            response.addProperty("titulo", exception.getNome());
            response.addProperty("descricao", exception.getDetalhe());

            return new ResponseEntity<>(new Gson().toJson(response), exception.getHttpStatus());
        }

        List<JsonObject> errors = new ArrayList<>();
        for (ConstraintViolation<Object> violation : exception.getViolations()) {
            JsonObject response = new JsonObject();
            response.addProperty("tipo", exception.getErrorException().getTipo());
            response.addProperty("titulo", exception.getErrorException().getNome());
            response.addProperty("descricao", StringUtils.capitalize(violation.getPropertyPath().toString()) + " " + violation.getMessage());
            errors.add(response);
        }
        return new ResponseEntity<>(new Gson().toJson(errors.size() > 1 ? errors : errors.get(0)),
                exception.getErrorException().getHttpStatus());
    }

    @ExceptionHandler(value = ErrorException.class)
    public ResponseEntity<Response> handleException(ErrorException exception) {
        return ResponseEntity.badRequest().body(exception.getResponse());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handler(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(ValidMessages.retornaMensagemErro(exception.getBindingResult()));

    }

    @ExceptionHandler
    public ResponseEntity<Response> defaultHandleException(Exception e) {
        Response response = new Response();
        response.setMessage(e.getCause() != null ? ExceptionUtils.getRootCauseMessage(e) : e.getMessage());
        response.setStackTrace(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.badRequest().body(response);
    }
}
