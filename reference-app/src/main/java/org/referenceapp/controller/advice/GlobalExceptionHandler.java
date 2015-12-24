package org.referenceapp.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by schinta6 on 12/23/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e){
        logException(e);
        return new ResponseEntity<String>("",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleException(HttpClientErrorException e){
        logException(e);
        if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return new ResponseEntity<String>("",HttpStatus.NOT_FOUND);
        }
        //TODO:need to review..
        return new ResponseEntity<String>("",e.getStatusCode());
    }

    private void logException(Exception e){
        LOG.error(e.getMessage(), e);
    }
}
