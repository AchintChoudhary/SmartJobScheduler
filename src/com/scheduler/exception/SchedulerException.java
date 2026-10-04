package com.scheduler.exception;

/*
 * Base custom checked exception for scheduler-specific errors.
 */
public class SchedulerException extends Exception {

    public SchedulerException(String message) {
        super(message);
    }
}
