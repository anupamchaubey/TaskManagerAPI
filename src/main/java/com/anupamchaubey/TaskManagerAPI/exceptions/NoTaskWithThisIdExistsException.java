package com.anupamchaubey.TaskManagerAPI.exceptions;

public class NoTaskWithThisIdExistsException extends RuntimeException {
    public NoTaskWithThisIdExistsException(String message) {
        super(message);
    }
}
