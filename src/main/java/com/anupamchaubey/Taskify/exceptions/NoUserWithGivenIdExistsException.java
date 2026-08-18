package com.anupamchaubey.Taskify.exceptions;

public class NoUserWithGivenIdExistsException extends RuntimeException {
    public NoUserWithGivenIdExistsException(String message) {
        super(message);
    }
}
