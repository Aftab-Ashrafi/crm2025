package com.crm.payload;



import lombok.Getter;


import java.util.Date;
@Getter

public class ErrorDetails {
    private  final Date date;
    private  final String message;
    private  final       String request;
    public ErrorDetails(Date date, String message ,String request) {
        this.date = date;
        this.message = message;
        this.request=request;

    }

}
