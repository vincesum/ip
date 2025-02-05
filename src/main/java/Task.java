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

    public String toString(Task[] storage, int i) {
        return (i + 1) + ")[" + getLabel() + "][" + storage[i].getStatusIcon() + "] " + storage[i].description + getDate();
    }

    public void print(Vinbot.printWelcomeMessage format, Task[] storage, int i) {
        System.out.println(format.spacing() + toString(storage, i));
    }
}
