package com.scopic.antiqueauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.io.IOException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FileStorageException.class,InvalidBidException.class, BindException.class})
    public final ResponseEntity<String> handleException(Exception ex, WebRequest request){
        if(ex instanceof FileStorageException){
            FileStorageException fileStorageException=(FileStorageException) ex;
            return new ResponseEntity<>(fileStorageException.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
        }
        if(ex instanceof InvalidBidException){
            InvalidBidException exception=(InvalidBidException) ex;
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        if(ex instanceof BindException){
            BindException bindException = (BindException) ex;
            String message=bindException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        if(ex instanceof IOException){
            IOException ioException=(IOException) ex;
            return new ResponseEntity<>(ioException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
