package Vinbot.Tasks;

import Vinbot.VinException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storage;
    private int numberOfElements;

    public TaskList() {
        storage = new ArrayList<>();
        numberOfElements = 0;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void addNumberOfElements() {
        numberOfElements++;
    }

    public void removeNumberOfElements() {
        numberOfElements--;
    }

    public void addTask(Task task) {
        storage.add(task);
        numberOfElements++;
    }

    public void removeTask(int taskIndex) throws VinException {
        storage.remove(taskIndex);
    }

    public Task getTask(int taskIndex) throws VinException {
        return storage.get(taskIndex);
    }
}
