package com.ambevtech.app.exception;

import java.util.List;

public class ErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private String messageException;
    private String stackTraceException;
    private List<String> errors;

    public String getMessageException() {
        return messageException;
    }

    public void setMessageException(String messageException) {
        this.messageException = messageException;
    }

    public String getStackTraceException() {
        return stackTraceException;
    }

    public void setStackTraceException(String stackTraceException) {
        this.stackTraceException = stackTraceException;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public ErrorException(String messageException) {
        this.messageException = messageException;
    }

    public ErrorException(String messageException, List<String> errors) {
        this.messageException = messageException;
        this.errors = errors;
    }

    public Response getResponse() {
        Response response = new Response();
        response.setMessage(messageException);
        response.setStackTrace(stackTraceException);
        response.setErrors(errors);
        return response;
    }

}
