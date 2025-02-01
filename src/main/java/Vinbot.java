import java.util.Arrays;
import java.util.Scanner;


public class Vinbot {
    public static void main(String[] args) {
        boolean isActive = true;    //Defines the operation status of the bot
        //Initialisation procedure
        String name = "Vinbot ^-^";
        String emptyLine = "----<<<<####****####>>>>----";
        String starLine = "****************************";
        String spacing = "    ";
        System.out.println(spacing + "Hello! I'm " + name);
        System.out.println(spacing + "What can I do for you?");
        System.out.println(spacing + "Please enter your current tasks");
        System.out.println(spacing + "Enter \"list\" to view your current tasks");
        System.out.println(spacing + "Enter \"mark\" followed by a number to mark task as complete");
        System.out.print(spacing + "Enter \"unmark\" followed by a number to mark task as incomplete\n" + spacing + emptyLine + "\n");
        //Storage
        Task[] storage = new Task[100]; //store the tasks
        int numberOfElements = 0;
        //Echo
        Scanner in = new Scanner(System.in);
        while (isActive) {
            String line;
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")) {    //exit loop
                isActive = false;
                break;
            } else if (line.toLowerCase().equals("list")) {    //displays list in array
                System.out.println(spacing + "Here are the tasks on your list: ^-^");
                int i = 0;
                while (storage[i] != null) {
                    System.out.println(spacing + (i + 1) + ")[" + storage[i].getStatusIcon() + "] " + storage[i].description);
                    i++;
                }
                System.out.println(spacing + starLine);
            } else if (line.toLowerCase().startsWith("mark")) {
                String intValue = line.replaceAll("[^0-9]", ""); //remove all non integers
                int integer = Integer.parseInt(intValue) - 1;
                if (integer >= 0 && integer < 100 && integer <= numberOfElements) {
                    storage[integer].isDone = true;
                    System.out.println("Good job on completing " + storage[integer].description + " [" + storage[integer].getStatusIcon() + "]");
                }
            } else if (line.toLowerCase().startsWith("unmark")) {
                String intValue = line.replaceAll("[^0-9]", ""); //remove all non integers
                int integer = Integer.parseInt(intValue) - 1;
                if (integer >= 0 && integer < 100 && integer <= numberOfElements) {
                    storage[integer].isDone = false;
                    System.out.println("Oh, you've unmarked the task " + storage[integer].description + " [" + storage[integer].getStatusIcon() + "] ;-;");
                }
            } else {
                storage[numberOfElements] = new Task(line);
                numberOfElements++;
                System.out.println(spacing + emptyLine);
                System.out.println(spacing + "added: " + line);
                System.out.println(spacing + starLine);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
