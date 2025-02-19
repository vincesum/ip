package Vinbot.Tasks;

import Vinbot.Vinbot;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    protected String getLabel() {
        return " ";
    }

    protected String getDate() {
        return "";
    }

    public String toString(ArrayList<Task> storage, int i) {
        return (i + 1) + ")[" + getLabel() + "][" + storage.get(i).getStatusIcon() + "] " + storage.get(i).description + getDate();
    }

    public void print(Vinbot.printWelcomeMessage format, ArrayList<Task> storage, int i) {
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
