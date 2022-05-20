package com.stdio.esm.exception;
import com.stdio.esm.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(value = {EsmException.class})
    public ResponseEntity<?> hrmException(Exception e) {
        return ResponseEntity.ok(ErrorResponse.bad(e.getMessage()));
    }


}
