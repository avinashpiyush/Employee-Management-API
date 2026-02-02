package com.apiexample_sep.exception;

import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
