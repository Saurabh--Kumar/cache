package com.saurabh.cache.exception;

public class CacheInitialisationException extends Exception{
    private final String exceptionMessage;

    public CacheInitialisationException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }
}
