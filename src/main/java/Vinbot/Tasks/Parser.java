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

    public int scanMessage(boolean isRunning, MessageFormat format, ArrayList<Task> storage, int numberOfElements, Scanner in) {
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
                listItems(format, storage);
                break;

            case "help":
                format.printHelpMessage(format);
                break;

            default:
                numberOfElements = handleMessage(format, storage, line, numberOfElements);
                break;
            }
        }
        return numberOfElements;
    }

    private int handleMessage(MessageFormat format, ArrayList<Task> storage, String line, int numberOfElements) {
        String command = line.split(" ")[0].toLowerCase(); // Extract the first word
        Storage fileHandler = new Storage();
        switch (command) {
        case "mark":
        case "unmark":
            try {
                handleMarkMessage(storage, line, numberOfElements, format);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "todo":
            try {
                numberOfElements = handleTodoMessage(format, storage, line, numberOfElements);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "deadline":
            try {
                numberOfElements = handleDeadLineMessage(format, storage, line, numberOfElements);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "event":
            try {
                numberOfElements = handleEventMessage(format, storage, line, numberOfElements);
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "delete":
            try {
                handleDeleteMessage(storage, line, numberOfElements, format);
                numberOfElements--;
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            try {
                throw new VinException("Hey! Sorry but I don't know what you've entered. GG.com", format);
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
        return numberOfElements;
    }

    private static int handleEventMessage(MessageFormat result, ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            String[] eventsData = Event.scan(result, line);
            Event event = new Event(eventsData[0], eventsData[1], eventsData[2]);
            storage.add(numberOfElements, event);
            numberOfElements++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numberOfElements;
    }

    private static int handleDeadLineMessage(MessageFormat result, ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            String[] deadLineData = Deadline.scan(result, line);
            Deadline deadLine = new Deadline(deadLineData[0], deadLineData[1]);
            storage.add(numberOfElements, deadLine);
            numberOfElements++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numberOfElements;
    }

    private static int handleTodoMessage(MessageFormat result, ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        if (!line.contains(" ")) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-", result);
        }
        line = line.substring(line.indexOf(" ") + 1).trim(); //records from the second word onwards
        if (line.trim().isEmpty()) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-", result);
        }
        Todo toDo = new Todo(line);
        storage.add(numberOfElements, toDo);
        toDo.scan(result, line);
        numberOfElements++;
        return numberOfElements;
    }

    private static void handleMarkMessage(ArrayList<Task> storage, String line, int numberOfElements, MessageFormat format) throws VinException {
        boolean mark = line.toLowerCase().startsWith("mark");
        String intValue = line.replaceAll("[^0-9]", ""); // remove all non-integers
        if (intValue.isEmpty()) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"), format);
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (taskIndex > numberOfElements - 1 || taskIndex < 0) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"), format);
        }
        storage.get(taskIndex).setDone(mark);
        System.out.println((mark ? "    Good job on completing " : "    Oh, you've unmarked the task ") +
                storage.get(taskIndex).getDescription() + " [" + storage.get(taskIndex).getStatusIcon() + "]" +
                (mark ? "" : " ;-;"));
    }

    private static void handleDeleteMessage(ArrayList<Task> storage, String line, int numberOfElements, MessageFormat format) throws VinException {
        String intValue = line.replaceAll("[^0-9]", "");
        if (intValue.isEmpty()) {
            throw new VinException("Error, no tasks were deleted", format);
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (taskIndex > numberOfElements - 1 || taskIndex < 0) {
            throw new VinException("Error, task index to delete is out of bounds ", format);
        }
        System.out.println(("    Successfully deleted " + storage.get(taskIndex).getDescription() + " [" + storage.get(taskIndex).getStatusIcon() + "]"));
        storage.remove(taskIndex);
    }

    private static void listItems(MessageFormat format, ArrayList<Task> storage) {
        System.out.println(format.getSpacing() + "Here are the tasks on your list: ^-^");
        int i = 0;
        while (i < storage.size()) {
            storage.get(i).print(format, storage, i);
            i++;
        }
        System.out.println(format.getSpacing() + format.getStarLine());
    }
}
