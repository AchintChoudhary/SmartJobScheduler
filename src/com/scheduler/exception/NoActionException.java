package com.scheduler.exception;

public class NoActionException extends SchedulerException {

    public NoActionException() {
        super("There is no action available to undo.");
    }
}
