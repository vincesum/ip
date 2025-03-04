package Vinbot;

import Vinbot.Tasks.Deadline;
import Vinbot.Tasks.Event;
import Vinbot.Tasks.TaskList;
import Vinbot.Tasks.Todo;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public boolean isActive;

    public Parser(boolean status) {
        isActive = status;
    }

    //Scans message based on keywords
    public void scanMessage(boolean isRunning, TaskList taskList, Scanner in) {
        String line;
        while (isRunning) {
            // Prevent NoSuchElementException
            if (!in.hasNextLine()) {
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
                UI.printHelpMessage();
                break;

            default: //In cases where command and text exceeds 1 word in length
                handleMessage(taskList, line);
                break;
            }
        }
    }

    //Handle messages exceeding 1 word in length
    private void handleMessage(TaskList storage, String line) {
        //Extract the first word
        String command = line.split(" ")[0].toLowerCase();
        Storage fileHandler = new Storage();
        switch (command) {
        case "mark":
        case "unmark":
            try {
                handleMarkMessage(storage, line);
            } catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;
        case "todo":
            try {
                handleTodoMessage(storage, line);
            } catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;
        case "deadline":
            try {
                handleDeadLineMessage(storage, line);
            } catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;
        case "event":
            try {
                handleEventMessage(storage, line);
            } catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;
        case "delete":
            try {
                handleDeleteMessage(storage, line);
            } catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;
        case "find":
            try {
                handleFindMessage(storage, line);
            } catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;

        //Default error message when Vinbot does not get a valid command
        default:
            try {
                throw new VinException("Hey! Sorry but I don't know what you've entered. GG.com");
            }
            catch (VinException e) {
                UI.showError(e.getMessage());
            }
            break;
        }
        //Write to storage file to keep track of files after current command
        try {
            fileHandler.writeToFile(storage);
        }
        catch (IOException e){
            UI.showError("Something went wrong with writing to Vinbot.txt: " + e.getMessage());
        }
    }

    //Breaks event message into 3 parts and stores them: description, from date, to date
    private static void handleEventMessage(TaskList storage, String line) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            //Breaks up the event data entered into description, from date and to date
            String[] eventsData = Event.scan(line);
            Event event = new Event(eventsData[0], eventsData[1], eventsData[2]);
            storage.addTask(event);
            //Catch exception where event data is entered in an incorrect format
        } catch (Exception e) {
            UI.showError(e.getMessage());
        }
    }

    //Breaks deadline message into 2 parts and stores them: description, by date
    private static void handleDeadLineMessage(TaskList storage, String line) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            //Breaks up the deadline data entered into description and by date
            String[] deadLineData = Deadline.scan(line);
            Deadline deadLine = new Deadline(deadLineData[0], deadLineData[1]);
            storage.addTask(deadLine);
            //Catch exception where deadline data is entered in an incorrect format
        } catch (Exception e) {
            UI.showError(e.getMessage());
        }
    }

    //Stores todo message
    private static void handleTodoMessage(TaskList storage, String line) throws VinException {
        if (!line.contains(" ")) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-");
        }
        //Records from the second word onwards
        line = line.substring(line.indexOf(" ") + 1).trim();
        if (line.trim().isEmpty()) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-");
        }
        Todo toDo = new Todo(line);
        storage.addTask(toDo);
        toDo.scan(line);
    }

    //Mark message based on index of message if not out of bounds
    private static void handleMarkMessage(TaskList storage, String line) throws VinException {
        boolean mark = line.toLowerCase().startsWith("mark");
        //Remove all non-integers
        String intValue = line.replaceAll("[^0-9]", "");
        //Case where int value is not given
        if (intValue.isEmpty()) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        //Handle edge cases
        if (taskIndex > storage.getNumberOfElements() - 1 || taskIndex < 0) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        storage.getTask(taskIndex).setDone(mark);
        if (mark) {
            UI.printLine("Good job on completing " + storage.getTask(taskIndex).getDescription() + " [" + storage.getTask(taskIndex).getStatusIcon() + "]");
        }
        else {
            UI.printLine("Oh, you've unmarked the task " + storage.getTask(taskIndex).getDescription() + " [" + storage.getTask(taskIndex).getStatusIcon() + "] ;-;");
        }
    }

    //Delete message based on index of message if not out of bounds
    private static void handleDeleteMessage(TaskList storage, String line) throws VinException {
        String intValue = line.replaceAll("[^0-9]", "");
        //Case where int value is not given
        if (intValue.isEmpty()) {
            throw new VinException("Error, no tasks were deleted");
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        //Handle edge cases
        if (taskIndex > storage.getNumberOfElements() - 1 || taskIndex < 0) {
            throw new VinException("Error, task index to delete is out of bounds ");
        }
        UI.printLine("Successfully deleted " + storage.getTask(taskIndex).getDescription() + " [" + storage.getTask(taskIndex).getStatusIcon() + "]");
        storage.removeTask(taskIndex);
    }

    //Finds message based on keyword
    private static void handleFindMessage(TaskList storage, String line) throws VinException {
        if (!line.contains(" ")) {
            throw new VinException("Uh oh! You did not enter any message to find!");
        }
        String findString = line.substring(line.indexOf(" ") + 1).trim();
        //Check for cases where space is entered after find
        if (findString.trim().isEmpty()) {
            throw new VinException("Uh oh! You did not enter any message to find!");
        }
        int index = 1; //Initialise a counter for listing found tasks
        //Print the tasks found
        for (int i = 0; i < storage.getNumberOfElements(); i++) {
            if (storage.getTask(i).getDescription().contains(findString)) {
                try {
                    UI.printFindTask(storage.getTask(i), i, index);
                    index++;
                }
                catch (VinException e) {
                    UI.showError(e.getMessage());
                }
            }
        }

    }

    //List items from index 1 to the number of tasks
    private static void listItems(TaskList taskList) {
        UI.printLine("Here are the tasks on your list: ^-^");
        int i = 0;
        while (i < taskList.getNumberOfElements()) {
            try {
                UI.printTask(taskList.getTask(i), i);
                i++;
            }
            catch (Exception e) {
                UI.showError(e.getMessage());
            }
        }
        UI.newLine();
    }
}
