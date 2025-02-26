package Vinbot.Tasks;

import Vinbot.MessageFormat;
import Vinbot.VinException;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import Vinbot.Storage;

public class Parser {
    public boolean isActive;

    public Parser(boolean status) {
        isActive = status;
    }

    public void scanMessage(boolean isRunning, TaskList taskList, Scanner in) {
        String line;
        while (isRunning) {
            if (!in.hasNextLine()) {  // Prevent NoSuchElementException
                break;
            }
            line = in.nextLine();
            switch (line.toLowerCase()) {
            case "bye":
                isRunning = false;
                break;

            case "list":
                listItems(taskList);
                break;

            case "help":
                MessageFormat.printHelpMessage();
                break;

            default:
                handleMessage(taskList, line);
                break;
            }
        }
    }

    private void handleMessage(TaskList storage, String line) {
        String command = line.split(" ")[0].toLowerCase(); // Extract the first word
        Storage fileHandler = new Storage();
        switch (command) {
        case "mark":
        case "unmark":
            try {
                handleMarkMessage(storage, line);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "todo":
            try {
                handleTodoMessage(storage, line);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "deadline":
            try {
                handleDeadLineMessage(storage, line);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "event":
            try {
                handleEventMessage(storage, line);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "delete":
            try {
                handleDeleteMessage(storage, line);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            try {
                throw new VinException("Hey! Sorry but I don't know what you've entered. GG.com");
            }
            catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        try {
            fileHandler.writeToFile(storage);
        }
        catch (IOException e){
            System.out.println("Something went wrong with writing to Vinbot.txt: " + e.getMessage());
        }
    }

    private static void handleEventMessage(TaskList storage, String line) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            String[] eventsData = Event.scan(line);
            Event event = new Event(eventsData[0], eventsData[1], eventsData[2]);
            storage.addTask(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleDeadLineMessage(TaskList storage, String line) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            String[] deadLineData = Deadline.scan(line);
            Deadline deadLine = new Deadline(deadLineData[0], deadLineData[1]);
            storage.addTask(deadLine);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleTodoMessage(TaskList storage, String line) throws VinException {
        if (!line.contains(" ")) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-");
        }
        line = line.substring(line.indexOf(" ") + 1).trim(); //records from the second word onwards
        if (line.trim().isEmpty()) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-");
        }
        Todo toDo = new Todo(line);
        storage.addTask(toDo);
        toDo.scan(line);
    }

    private static void handleMarkMessage(TaskList storage, String line) throws VinException {
        boolean mark = line.toLowerCase().startsWith("mark");
        String intValue = line.replaceAll("[^0-9]", ""); // remove all non-integers
        if (intValue.isEmpty()) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (taskIndex > storage.getNumberOfElements() - 1 || taskIndex < 0) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        storage.getTask(taskIndex).setDone(mark);
        System.out.println((mark ? "    Good job on completing " : "    Oh, you've unmarked the task ") +
                storage.getTask(taskIndex).getDescription() + " [" + storage.getTask(taskIndex).getStatusIcon() + "]" +
                (mark ? "" : " ;-;"));
    }

    private static void handleDeleteMessage(TaskList storage, String line) throws VinException {
        String intValue = line.replaceAll("[^0-9]", "");
        if (intValue.isEmpty()) {
            throw new VinException("Error, no tasks were deleted");
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (taskIndex > storage.getNumberOfElements() - 1 || taskIndex < 0) {
            throw new VinException("Error, task index to delete is out of bounds ");
        }
        System.out.println(("    Successfully deleted " + storage.getTask(taskIndex).getDescription() + " [" + storage.getTask(taskIndex).getStatusIcon() + "]"));
        storage.removeTask(taskIndex);
    }

    private static void listItems(TaskList taskList) {
        System.out.println(MessageFormat.getSpacing() + "Here are the tasks on your list: ^-^");
        int i = 0;
        while (i < taskList.getNumberOfElements()) {
            try {
                taskList.getTask(i).print(taskList, i);
                i++;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(MessageFormat.getSpacing() + MessageFormat.getStarLine());
    }
}
