package com.scheduler;

import com.scheduler.exception.SchedulerException;
import com.scheduler.service.JobScheduler;
import com.scheduler.util.InputHelper;

public class Main {

    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler();

        while (true) {
            scheduler.displayMenu();
            int choice = InputHelper.readInt("Enter command: ");

            try {
                switch (choice) {
                    case 1 -> scheduler.addJob();
                    case 2 -> scheduler.removeJob();
                    case 3 -> scheduler.viewAllJobs();
                    case 4 -> scheduler.searchJob();
                    case 5 -> scheduler.executeNextJob();
                    case 6 -> scheduler.viewJobHistory();
                    case 7 -> scheduler.undoLastAction();
                    case 8 -> scheduler.sortJobsByPriority();
                    case 9 -> {
                        System.out.println("\nThank you for using Smart Job Scheduler!");
                        System.out.println("Exiting...");
                        InputHelper.close();
                        return;
                    }
                    default -> throw new SchedulerException(
                            "Invalid menu option. Please choose between 1 and 9."
                    );
                }
            } catch (SchedulerException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
