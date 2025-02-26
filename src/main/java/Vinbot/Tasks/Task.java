package Vinbot.Tasks;

import Vinbot.MessageFormat;
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

    public String toString(TaskList storage, int i) {
        String string = "";
        try {
            string = (i + 1) + ")[" + getLabel() + "][" + storage.getTask(i).getStatusIcon() + "] " + storage.getTask(i).description + getDate();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return string;
    }

    public void print(TaskList storage, int i) {
        System.out.println(MessageFormat.getSpacing() + toString(storage, i));
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
