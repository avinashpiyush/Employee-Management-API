package com.apiexample_sep.exception;

import com.apiexample_sep.dto.ErrorDto;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFoundExceptionHandler(
            ResourceNotFoundException e,
            WebRequest request
    ){
        ErrorDto dto=new ErrorDto(new Date(),e.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
