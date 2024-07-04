package com.inventory.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExistException extends RuntimeException{
    public ExistException(String message){
        super(message);
    }
}
