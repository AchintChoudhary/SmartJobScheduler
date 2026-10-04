package com.scheduler.exception;

public class DuplicateJobException extends SchedulerException {

    public DuplicateJobException(int id) {
        super("Job with ID " + id + " already exists.");
    }
}
