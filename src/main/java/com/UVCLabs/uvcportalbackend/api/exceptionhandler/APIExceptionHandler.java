package com.UVCLabs.uvcportalbackend.api.exceptionhandler;

import com.UVCLabs.uvcportalbackend.domain.exception.BusinessException;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var error = new Message();
        error.setStatus(status.value());
        error.setTitle(ex.getMessage());
        error.setDate(LocalDateTime.now());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
