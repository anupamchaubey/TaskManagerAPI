package com.anupamchaubey.Taskify.exceptions;

public class NoTaskWithThisIdExistsException extends RuntimeException {
    public NoTaskWithThisIdExistsException(String message) {
        super(message);
    }
}
