package org.referenceapp.io;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by schinta6 on 12/23/15.
 */
public class JsonResponse<T> {

    private Boolean success = Boolean.TRUE;

    private String globalError;

    @Override
    public String toString() {
        return "JsonResponse [success=" + success + ", globalError=" + globalError + ", errors=" + errors + ", result="
                + result + ", status=" + status + "]";
    }

    private Map<String, String> errors = new HashMap<String, String>();

    private T result;

    @JsonIgnore
    private Integer status;

    public static <T> JsonResponse<T> createSuccess() {
        final JsonResponse<T> response = new JsonResponse<T>();
        response.setSuccess(true);
        return response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    @JsonInclude(Include.NON_NULL)
    public T getResult() {
        return result;
    }

    public void setResult(final T result) {
        this.result = result;
    }

    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(final String key, final String value) {
        errors.put(key, value);
    }

    public void setErrors(final Map<String, String> errors) {
        this.errors = errors;
    }

    @JsonInclude(Include.NON_NULL)
    public String getGlobalError() {
        return globalError;
    }

    public void setGlobalError(final String globalError) {
        this.globalError = globalError;
    }

    public static JsonResponse<Void> create400BadRequest() {
        final JsonResponse<Void> response = new JsonResponse<Void>();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return response;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }



}
