package com.scheduler.exception;

public class InvalidPriorityException extends SchedulerException {

    public InvalidPriorityException(int priority) {
        super("Invalid priority: " + priority + ". Priority must be between 1 and 5.");
    }
}
