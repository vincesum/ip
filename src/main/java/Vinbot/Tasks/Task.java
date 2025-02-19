package Vinbot.Tasks;

import Vinbot.Vinbot;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getLabel() {
        return " ";
    }

    public String getDate() {
        return "";
    }

    public String getBy() {
        return "";
    }
    public String getStart() {
        return "";
    }
    public String getEnd() {
        return "";
    }

    public String toString(Task[] storage, int i) {
        return (i + 1) + ")[" + getLabel() + "][" + storage[i].getStatusIcon() + "] " + storage[i].description + getDate();
    }

    public void print(Vinbot.printWelcomeMessage format, Task[] storage, int i) {
        System.out.println(format.spacing() + toString(storage, i));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
