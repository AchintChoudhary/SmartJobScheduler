package com.scheduler.service;

import com.scheduler.exception.DuplicateJobException;
import com.scheduler.exception.JobNotFoundException;
import com.scheduler.exception.NoActionException;
import com.scheduler.exception.NoPendingJobException;
import com.scheduler.exception.SchedulerException;
import com.scheduler.model.Action;
import com.scheduler.model.Job;
import com.scheduler.util.InputHelper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Service class:
 * contains the application's business logic.
 *
 * OOP concepts demonstrated:
 * - Encapsulation
 * - Composition (JobScheduler owns collections)
 * - Abstraction through methods such as addJob(), executeNextJob()
 */
public class JobScheduler {

    private final Map<Integer, Job> jobs = new HashMap<>();
    private final List<Job> jobList = new ArrayList<>();

    private final PriorityQueue<Job> jobQueue =
            new PriorityQueue<>(
                    Comparator.comparingInt(Job::getPriority).reversed()
            );

    private final Deque<Job> jobHistory = new ArrayDeque<>();
    private final Deque<Action> actionHistory = new ArrayDeque<>();

    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("          SMART JOB SCHEDULER");
        System.out.println("========================================");
        System.out.println("1. Add Job");
        System.out.println("2. Remove Job");
        System.out.println("3. View All Jobs");
        System.out.println("4. Search Job");
        System.out.println("5. Execute Next Job");
        System.out.println("6. View Job History");
        System.out.println("7. Undo Last Action");
        System.out.println("8. Sort Jobs by Priority");
        System.out.println("9. Exit");
        System.out.println("========================================");
    }

    public void addJob() throws SchedulerException {
        System.out.println("\n===== ADD JOB =====");

        int id = InputHelper.readPositiveInt("Enter Job ID: ");

        if (jobs.containsKey(id)) {
            throw new DuplicateJobException(id);
        }

        String title = InputHelper.readNonEmptyString("Enter Job Title: ");
        int priority = InputHelper.readPriority("Enter Priority (1-5): ");

        Job job = new Job(id, title, priority);

        jobs.put(id, job);
        jobList.add(job);
        jobQueue.offer(job);
        actionHistory.push(new Action(Action.Type.ADD, job));

        System.out.println("\nJob added successfully!");
        System.out.println(job);
    }

    public void removeJob() throws SchedulerException {
        System.out.println("\n===== REMOVE JOB =====");

        int id = InputHelper.readPositiveInt("Enter Job ID to remove: ");
        Job job = jobs.get(id);

        if (job == null) {
            throw new JobNotFoundException(id);
        }

        jobs.remove(id);
        jobList.remove(job);
        jobQueue.remove(job);

        actionHistory.push(new Action(Action.Type.REMOVE, job));

        System.out.println("Job removed successfully!");
    }

    public void viewAllJobs() {
        System.out.println("\n===== ALL JOBS =====");

        if (jobList.isEmpty()) {
            System.out.println("No jobs available.");
            return;
        }

        for (Job job : jobList) {
            System.out.println(job);
        }
    }

    public void searchJob() throws SchedulerException {
        System.out.println("\n===== SEARCH JOB =====");

        int id = InputHelper.readPositiveInt("Enter Job ID: ");
        Job job = jobs.get(id);

        if (job == null) {
            throw new JobNotFoundException(id);
        }

        System.out.println("\n===== JOB FOUND =====");
        System.out.println(job);
    }

    public void executeNextJob() throws SchedulerException {
        System.out.println("\n===== EXECUTE NEXT JOB =====");

        Job job = jobQueue.poll();

        if (job == null) {
            throw new NoPendingJobException();
        }

        job.markCompleted();
        jobHistory.addLast(job);
        actionHistory.push(new Action(Action.Type.EXECUTE, job));

        System.out.println("\nExecuting Job...");
        System.out.println(job);
    }

    public void viewJobHistory() {
        System.out.println("\n===== JOB HISTORY =====");

        if (jobHistory.isEmpty()) {
            System.out.println("No completed jobs yet.");
            return;
        }

        int count = 1;
        for (Job job : jobHistory) {
            System.out.println(count++ + ". " + job);
        }
    }

    public void undoLastAction() throws SchedulerException {
        System.out.println("\n===== UNDO LAST ACTION =====");

        if (actionHistory.isEmpty()) {
            throw new NoActionException();
        }

        Action action = actionHistory.pop();
        Job job = action.getJob();

        switch (action.getType()) {
            case ADD -> undoAdd(job);
            case REMOVE -> undoRemove(job);
            case EXECUTE -> undoExecute(job);
        }
    }

    private void undoAdd(Job job) {
        jobs.remove(job.getId());
        jobList.remove(job);
        jobQueue.remove(job);

        System.out.println(
                "Undo successful: Job " + job.getId() + " was removed."
        );
    }

    private void undoRemove(Job job) {
        jobs.put(job.getId(), job);
        jobList.add(job);

        if ("PENDING".equals(job.getStatus())) {
            jobQueue.offer(job);
        }

        System.out.println(
                "Undo successful: Job " + job.getId() + " was restored."
        );
    }

    private void undoExecute(Job job) {
        job.markPending();
        jobQueue.offer(job);
        jobHistory.removeLastOccurrence(job);

        System.out.println(
                "Undo successful: Job " + job.getId() + " is PENDING again."
        );
    }

    public void sortJobsByPriority() {
        System.out.println("\n===== JOBS SORTED BY PRIORITY =====");

        if (jobList.isEmpty()) {
            System.out.println("No jobs available.");
            return;
        }

        List<Job> sortedJobs = new ArrayList<>(jobList);

        sortedJobs.sort(
                Comparator.comparingInt(Job::getPriority).reversed()
        );

        for (Job job : sortedJobs) {
            System.out.println(job);
        }
    }
}
