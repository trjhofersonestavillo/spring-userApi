package com.exampleuserApi.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exampleuserApi.exceptions.InvalidRequestBodyException;
import com.exampleuserApi.pojo.ApiResponse;
import lombok.Builder;

@Builder
@ControllerAdvice
public class RestApiHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity notFoundHandler(Exception ex) {
        return ResponseEntity.status(404).body(ApiResponse.builder()
                .code(404).status("NOT FOUND").message(ex.getMessage()).build());
    }


    @ExceptionHandler({InvalidRequestBodyException.class})
    public ResponseEntity unprocessableEntityHandler(Exception ex) {
        return ResponseEntity.status(422).body(ApiResponse.builder()
                .code(422).status("UNPROCESSABLE ENTITY").message(ex.getMessage()).build());
    }

}
