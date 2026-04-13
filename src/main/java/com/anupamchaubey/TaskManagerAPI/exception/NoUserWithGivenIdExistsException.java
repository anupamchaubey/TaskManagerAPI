package com.anupamchaubey.TaskManagerAPI.exception;

public class NoUserWithGivenIdExistsException extends RuntimeException {
    public NoUserWithGivenIdExistsException(String message) {
        super(message);
    }
}
