package com.scheduler.exception;

public class NoPendingJobException extends SchedulerException {

    public NoPendingJobException() {
        super("No pending jobs are available for execution.");
    }
}
