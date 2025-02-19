package Vinbot.Tasks;

import Vinbot.Vinbot;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import Vinbot.FileEditor;

public class LineReader {
    public boolean isActive;

    public LineReader(boolean status) {
        isActive = status;
    }

    public int scanMessage(boolean isRunning, Vinbot.printWelcomeMessage format, ArrayList<Task> storage,int numberOfElements, Scanner in) {
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
                Vinbot.printHelpMessage(format);
                break;

            default:
                numberOfElements = handleMessage(format, storage, line, numberOfElements);
                break;
            }
        }
        return numberOfElements;
    }

    private static int handleEventMessage(Vinbot.printWelcomeMessage result, ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            String[] eventsData = Events.scan(result, line);
            Events event = new Events(eventsData[0], eventsData[1], eventsData[2]);
            storage.add(numberOfElements, event);
            numberOfElements++;
        } catch (Exception e) {
            throw new VinException(e.getMessage());
        }
        return numberOfElements;
    }

    private static int handleDeadLineMessage(Vinbot.printWelcomeMessage result, ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        try {
            String[] deadLineData = Deadlines.scan(result, line);
            Deadlines deadLine = new Deadlines(deadLineData[0], deadLineData[1]);
            storage.add(numberOfElements, deadLine);
            numberOfElements++;
        } catch (Exception e) {
            throw new VinException(e.getMessage());
        }
        return numberOfElements;
    }

    private static int handleTodoMessage(Vinbot.printWelcomeMessage result, ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        if (!line.contains(" ")) {
            throw new VinException("    Darn, your description of a todo is empty. Please enter something -.-");
        }
        line = line.substring(line.indexOf(" ") + 1).trim(); //records from the second word onwards
        if (line.trim().isEmpty()) {
            throw new VinException("    Darn, your description of a todo is empty. Please enter something -.-");
        }
        Todos toDo = new Todos(line);
        storage.add(numberOfElements, toDo);
        toDo.scan(result, line);
        numberOfElements++;
        return numberOfElements;
    }

    private static void handleMarkMessage(ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        boolean mark = line.toLowerCase().startsWith("mark");
        String intValue = line.replaceAll("[^0-9]", ""); // remove all non-integers
        if (intValue.isEmpty()) {
            throw new VinException("    Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (taskIndex > numberOfElements - 1 || taskIndex < 0) {
            throw new VinException("    Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        storage.get(taskIndex).setDone(mark);
        System.out.println((mark ? "    Good job on completing " : "    Oh, you've unmarked the task ") +
                storage.get(taskIndex).getDescription() + " [" + storage.get(taskIndex).getStatusIcon() + "]" +
                (mark ? "" : " ;-;"));
    }

    private static void handleDeleteMessage(ArrayList<Task> storage, String line, int numberOfElements) throws VinException {
        String intValue = line.replaceAll("[^0-9]", "");
        if (intValue.isEmpty()) {
            throw new VinException("    Error, no tasks were deleted");
        }
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (taskIndex > numberOfElements - 1 || taskIndex < 0) {
            throw new VinException("    Error, task index to delete is out of bounds ");
        }
        System.out.println(("    Successfully deleted " + storage.get(taskIndex).getDescription() + " [" + storage.get(taskIndex).getStatusIcon() + "]"));
        storage.remove(taskIndex);
    }

    private int handleMessage(Vinbot.printWelcomeMessage format, ArrayList<Task> storage, String line, int numberOfElements) {
        String command = line.split(" ")[0].toLowerCase(); // Extract the first word
        FileEditor fileHandler = new FileEditor();
        switch (command) {
        case "mark":
        case "unmark":
            try {
                handleMarkMessage(storage, line, numberOfElements);
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
                handleDeleteMessage(storage, line, numberOfElements);
                numberOfElements--;
            } catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            System.out.println("    Hey! Sorry but I don't know what you've entered. GG.com");
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

    private static void listItems(Vinbot.printWelcomeMessage format, ArrayList<Task> storage) {
        System.out.println(format.spacing() + "Here are the tasks on your list: ^-^");
        int i = 0;
        while (i < storage.size()) {
            storage.get(i).print(format, storage, i);
            i++;
        }
        System.out.println(format.spacing() + format.starLine());
    }
}
