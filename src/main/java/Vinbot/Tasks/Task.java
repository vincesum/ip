package Vinbot.Tasks;

import Vinbot.UI;

//Parent class for Todo, Event and Deadline. Simulates a task
public class Task {
    protected String description;
    protected boolean isDone;

    //Constructor
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

    /**
     * Returns a String to display the information of the task with the current index i and task index index.
     * @param i
     * @param index
     * */
    public String toString(int i, int index) {
        String string = "";
        try {
            string = "(" + index + ")[" + getLabel() + "][" + getStatusIcon() + "] " + getDescription() + " " + getDate();
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
