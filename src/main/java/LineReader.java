import java.util.Scanner;

public class LineReader {
    public boolean isActive;
    public static final Scanner in = new Scanner(System.in);

    public LineReader(boolean status) {
        isActive = status;
    }
    public void scanMessage(boolean isRunning, Vinbot.printWelcomeMessage format, Task[] storage) {
        int numberOfElements = 0;
        String line = "";
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
    }

    private static int handleEventMessage(Vinbot.printWelcomeMessage result, Task[] storage, String line, int numberOfElements) {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        String[] eventsData = Events.scan(result, line);
        if (eventsData != null) {
            Events event = new Events(eventsData[0], eventsData[1], eventsData[2]);
            storage[numberOfElements] = event;
            numberOfElements++;
        }
        return numberOfElements;
    }

    private static int handleDeadLineMessage(Vinbot.printWelcomeMessage result, Task[] storage, String line, int numberOfElements) {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        String[] deadLineData = Deadlines.scan(result, line);
        if (deadLineData != null) {
            Deadlines deadLine = new Deadlines(deadLineData[0], deadLineData[1]);
            storage[numberOfElements] = deadLine;
            numberOfElements++;
        }
        return numberOfElements;
    }

    private static int handleTodoMessage(Vinbot.printWelcomeMessage result, Task[] storage, String line, int numberOfElements) throws VinException {
        if (!line.contains(" ")) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-");
        }
        line = line.substring(line.indexOf(" ") + 1).trim(); //records from the second word onwards
        if (line.trim().isEmpty()) {
            throw new VinException("Darn, your description of a todo is empty. Please enter something -.-");
        }
        Todos toDo = new Todos(line);
        storage[numberOfElements] = toDo;
        toDo.scan(result, line);
        numberOfElements++;
        return numberOfElements;
    }

    private static void handleMarkMessage(Task[] storage, String line, int numberOfElements) throws VinException {
        boolean mark = line.toLowerCase().startsWith("mark");
        String intValue = line.replaceAll("[^0-9]", ""); // remove all non-integers
        int taskIndex = Integer.parseInt(intValue) - 1;
        if (intValue.isEmpty() || taskIndex > numberOfElements - 1 || taskIndex < 0) {
            throw new VinException("Error, invalid task " + (mark ? "marked" : "unmarked"));
        }
        if (taskIndex >= 0 && taskIndex <= 100) {
            storage[taskIndex].isDone = mark;
            System.out.println((mark ? "Good job on completing " : "Oh, you've unmarked the task ") +
                    storage[taskIndex].description + " [" + storage[taskIndex].getStatusIcon() + "]" +
                    (mark ? "" : " ;-;"));
        }
    }

    private int handleMessage(Vinbot.printWelcomeMessage format, Task[] storage, String line, int numberOfElements) {
        String command = line.split(" ")[0].toLowerCase(); // Extract the first word

        switch (command) {
        case "mark":
        case "unmark":
            try {
                handleMarkMessage(storage, line, numberOfElements);
            }
            catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "todo":
            try {
                numberOfElements = handleTodoMessage(format, storage, line, numberOfElements);
            }
            catch (VinException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "deadline":
            numberOfElements = handleDeadLineMessage(format, storage, line, numberOfElements);
            break;
        case "event":
            numberOfElements = handleEventMessage(format, storage, line, numberOfElements);
            break;
        default:
            System.out.println("Hey! Sorry but I don't know what you've entered. GG.com");
            break;
        }

        return numberOfElements;
    }

    private static void listItems(Vinbot.printWelcomeMessage format, Task[] storage) {
        System.out.println(format.spacing() + "Here are the tasks on your list: ^-^");
        int i = 0;
        while (storage[i] != null) {
            storage[i].print(format, storage, i);
            i++;
        }
        System.out.println(format.spacing() + format.starLine());
    }
}
