package com.scheduler.model;

public class Action {

    public enum Type {
        ADD,
        REMOVE,
        EXECUTE
    }

    private final Type type;
    private final Job job;

    public Action(Type type, Job job) {
        this.type = type;
        this.job = job;
    }

    public Type getType() {
        return type;
    }

    public Job getJob() {
        return job;
    }
}
