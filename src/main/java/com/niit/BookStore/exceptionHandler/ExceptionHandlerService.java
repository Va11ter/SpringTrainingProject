package com.niit.BookStore.exceptionHandler;

import com.niit.BookStore.exception.ItemNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerService {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handler (ItemNotFoundException ex, WebRequest request){
        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
