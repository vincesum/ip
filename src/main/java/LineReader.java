import java.util.Scanner;

public class LineReader {
    public boolean isActive;

    public LineReader(boolean status) {
        isActive = status;
    }

    public void scanMessage(boolean status, Vinbot.printWelcomeMessage format, Task[] storage) {
        String line;
        Scanner in = new Scanner(System.in);
        int numberOfElements = 0;
        while (status) {
            line = in.nextLine();
            switch (line.toLowerCase()) {
            case "bye":
                status = false;
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

    private static int handleTaskMessage(Vinbot.printWelcomeMessage result, Task[] storage, String line, int numberOfElements) {
        storage[numberOfElements] = new Task(line);
        numberOfElements++;
        System.out.println(result.spacing() + result.emptyLine());
        System.out.println(result.spacing() + "added: " + line);
        System.out.println(result.spacing() + result.starLine());
        return numberOfElements;
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

    private static int handleTodoMessage(Vinbot.printWelcomeMessage result, Task[] storage, String line, int numberOfElements) {
        line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
        Todos toDo = new Todos(line);
        storage[numberOfElements] = toDo;
        toDo.scan(result, line);
        numberOfElements++;
        return numberOfElements;
    }

    private static void handleMarkMessage(Task[] storage, String line, int numberOfElements) {
        boolean mark = line.toLowerCase().startsWith("mark");
        String intValue = line.replaceAll("[^0-9]", ""); // remove all non-integers

        if (intValue.isEmpty()) {
            System.out.println("Error, invalid task " + (mark ? "marked" : "unmarked"));
            return;
        }

        int integer = Integer.parseInt(intValue) - 1;
        if (integer >= 0 && integer < 100 && integer <= numberOfElements) {
            storage[integer].isDone = mark;
            System.out.println((mark ? "Good job on completing " : "Oh, you've unmarked the task ") +
                    storage[integer].description + " [" + storage[integer].getStatusIcon() + "]" +
                    (mark ? "" : " ;-;"));
        }
    }

    private int handleMessage(Vinbot.printWelcomeMessage format, Task[] storage, String line, int numberOfElements) {
        if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) {
            handleMarkMessage(storage, line, numberOfElements);
        }
        else if (line.toLowerCase().startsWith("todo")) {
            numberOfElements = handleTodoMessage(format, storage, line, numberOfElements);
        }
        else if (line.toLowerCase().startsWith("deadline")) {
            numberOfElements = handleDeadLineMessage(format, storage, line, numberOfElements);
        }
        else if (line.toLowerCase().startsWith("event")) {
            numberOfElements = handleEventMessage(format, storage, line, numberOfElements);
        }
        else {
            numberOfElements = handleTaskMessage(format, storage, line, numberOfElements);
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
