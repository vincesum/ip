package Vinbot.Tasks;

import Vinbot.MessageFormat;
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

    public void addTask(Task task) {
        storage.add(task);
        numberOfElements++;
    }

    public void removeTask(int taskIndex) throws VinException {
        storage.remove(taskIndex);
        numberOfElements--;
    }

    public Task getTask(int taskIndex) throws VinException {
        if (taskIndex < 0 || taskIndex >= storage.size()) {
            throw new VinException("Task Index out of bounds!");
        }
        return storage.get(taskIndex);
    }
}
