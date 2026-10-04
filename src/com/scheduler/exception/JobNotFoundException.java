package com.scheduler.exception;

public class JobNotFoundException extends SchedulerException {

    public JobNotFoundException(int id) {
        super("Job with ID " + id + " was not found.");
    }
}
