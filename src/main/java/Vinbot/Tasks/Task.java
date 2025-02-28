package Vinbot.Tasks;

import Vinbot.UI;

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

    //For overrides
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

    public String toString(int i) {
        String string = "";
        try {
            string = (i + 1) + ")[" + getLabel() + "][" + getStatusIcon() + "] " + getDescription() + " " + getDate();
        }
        catch (Exception e) {
            UI.showError(e.getMessage());
        }
        return string;
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
