package com.mun0n.domain.exception;

public interface ErrorBundle {
    
    Exception getException();
    
    String getErrorMessage();
}
