package org.hardware.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerConf {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> ConnectionExceptionHandler(ConnectException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("message","Product Service is not up!!");
        log.error(HttpStatus.BAD_GATEWAY+" Product Service is not up!!");
        return new ResponseEntity<>(body,HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("message","Please check your input!!!");
        return new ResponseEntity<>(body,HttpStatus.BAD_GATEWAY);
    }
}
