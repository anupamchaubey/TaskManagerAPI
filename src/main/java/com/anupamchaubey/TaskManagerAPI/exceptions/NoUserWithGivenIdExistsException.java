package com.anupamchaubey.TaskManagerAPI.exceptions;

public class NoUserWithGivenIdExistsException extends RuntimeException {
    public NoUserWithGivenIdExistsException(String message) {
        super(message);
    }
}
