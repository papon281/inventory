package com.inventory.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotMatchedException extends RuntimeException{
    public NotMatchedException(String message){
        super(message);
    }
}
