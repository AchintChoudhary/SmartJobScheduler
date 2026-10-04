package com.scheduler.model;

/*
 * OOP concept:
 * Encapsulation - fields are private and accessed through methods.
 */
public class Job {
    private final int id;
    private final String title;
    private final int priority;
    private String status;

    public Job(int id, String title, int priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = "PENDING";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void markCompleted() {
        this.status = "COMPLETED";
    }

    public void markPending() {
        this.status = "PENDING";
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %-5d | Title: %-25s | Priority: %-2d | Status: %s",
                id, title, priority, status
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Job)) return false;
        Job other = (Job) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
