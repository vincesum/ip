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

    public String toString(Vinbot.printWelcomeMessage result, Task storage[], int i) {
        return (i + 1) + ")[" + getLabel() + "][" + storage[i].getStatusIcon() + "] " + storage[i].description;
    }

    public void print(Vinbot.printWelcomeMessage result, Task storage[], int i) {
        System.out.println(result.spacing() + toString(result, storage, i));
    }
}
