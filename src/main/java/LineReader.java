import java.util.Scanner;

public class LineReader {
    public boolean isActive;

    public LineReader(boolean status) {
        isActive = status;
    }

    public String readLine() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void scanMessage(boolean status, Vinbot.printWelcomeMessage result, Task storage[]) {
        String line;
        int numberOfElements = 0;
        while (status) {
            line = readLine();
            switch (line.toLowerCase()) {
            case "bye":
                status = false;
                break;

            case "list":
                listItems(result, storage);
                break;

            default:
                if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) {
                    boolean mark = line.toLowerCase().startsWith("mark");
                    String intValue = line.replaceAll("[^0-9]", ""); // remove all non-integers

                    if (intValue.isEmpty()) {
                        System.out.println("Error, invalid task " + (mark ? "marked" : "unmarked"));
                        break;
                    }

                    int integer = Integer.parseInt(intValue) - 1;
                    if (integer >= 0 && integer < 100 && integer <= numberOfElements) {
                        storage[integer].isDone = mark;
                        System.out.println((mark ? "Good job on completing " : "Oh, you've unmarked the task ") +
                                storage[integer].description + " [" + storage[integer].getStatusIcon() + "]" +
                                (mark ? "" : " ;-;"));
                    }
                }
                else if (line.toLowerCase().startsWith("todo")) {
                    line = line.substring(line.indexOf(" ") + 1); //records from the second word onwards
                    Todos todo = new Todos(line);
                    storage[numberOfElements] = todo;
                    todo.scan(result, line);
                    numberOfElements++;
                }
                else {
                    storage[numberOfElements] = new Task(line);
                    numberOfElements++;
                    System.out.println(result.spacing() + result.emptyLine());
                    System.out.println(result.spacing() + "added: " + line);
                    System.out.println(result.spacing() + result.starLine());
                }
                break;
            }
        }
    }

    private static void listItems(Vinbot.printWelcomeMessage result, Task[] storage) {
        System.out.println(result.spacing() + "Here are the tasks on your list: ^-^");
        int i = 0;
        while (storage[i] != null) {
            storage[i].print(result, storage, i);
            i++;
        }
        System.out.println(result.spacing() + result.starLine());
        return;
    }
}
