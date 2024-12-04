package com.crm.exception;

import com.crm.payload.ErrorDetails;
import org.springframework.aop.config.AdvisorComponentDefinition;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//catch block it behaves as global catch block
@ControllerAdvice
public class HandleException {



    //Handles exception for resource not found
    @ExceptionHandler(ResourceNotFound.class)

    public ResponseEntity<ErrorDetails> handleResourceNotFound(
            ResourceNotFound e,
            WebRequest request
    ) {

        ErrorDetails errorDetails=new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorDetails> globalException(
            Exception e,
            WebRequest request
    ) {

        ErrorDetails errorDetails=new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
