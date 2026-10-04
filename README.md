# Smart Job Scheduler - Java Console Project

## Purpose

A command-line Java application that manages jobs/tasks and executes pending
jobs according to priority.

## Java concepts used

- Object-Oriented Programming
- Encapsulation
- Composition
- Java Collections Framework
- Generics
- Lambda expressions
- Comparator
- Custom checked exceptions
- Exception handling
- Centralized input handling
- Enum

## Data structures

- HashMap<Integer, Job> - average O(1) lookup by ID
- ArrayList<Job> - display and sorting
- PriorityQueue<Job> - highest-priority pending job first
- Deque<Job> - completed-job history
- Deque<Action> - undo stack

## Custom exceptions

- SchedulerException
- DuplicateJobException
- JobNotFoundException
- InvalidPriorityException
- NoPendingJobException
- NoActionException

## Compile

From the project root:

```bash
javac -d out src/com/scheduler/model/*.java src/com/scheduler/exception/*.java src/com/scheduler/service/*.java src/com/scheduler/util/*.java src/com/scheduler/Main.java
```

## Run

```bash
java -cp out com.scheduler.Main
```

## Example

Add:

101 - Database Backup - Priority 2
102 - Payment Processing - Priority 5
103 - Send Email - Priority 3

Execution order:

102 -> 103 -> 101
