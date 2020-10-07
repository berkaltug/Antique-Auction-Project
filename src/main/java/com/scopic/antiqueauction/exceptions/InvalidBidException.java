package com.scopic.antiqueauction.exceptions;

public class InvalidBidException extends RuntimeException{

    public InvalidBidException(String message) {
        super(message);
    }

    public InvalidBidException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
