package org.referenceapp.controller.advice;

import org.referenceapp.io.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by schinta6 on 12/23/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger("app");

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResponse<String>> handleGenericException(Exception e){
        JsonResponse<String> jsonResponse = createJsonErrorResponse(e);

        return new ResponseEntity<JsonResponse<String>>(jsonResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private JsonResponse<String> createJsonErrorResponse(Exception e) {
        String corelationId = UUID.randomUUID().toString();
        LOG.error(corelationId,e.getMessage(),e);

        Map<String,String> errors = new HashMap<String, String>();
        errors.put("ErrorCode",corelationId);

        JsonResponse<String> jsonResponse = new JsonResponse<String>();
        jsonResponse.setSuccess(false);
        jsonResponse.setErrors(errors);

        return jsonResponse;
    }

}
