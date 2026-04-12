package com.anupamchaubey.TaskManagerAPI.exception;

public class NoTaskWithThisIdExistsException extends RuntimeException {
    public NoTaskWithThisIdExistsException(String message) {
        super(message);
    }
}
