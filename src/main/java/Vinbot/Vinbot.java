package Vinbot;

import Vinbot.Tasks.LineReader;
import Vinbot.Tasks.Task;


public class Vinbot {

    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {

        //Initialisation procedure
        printWelcomeMessage format = getMessage();

        //Storage
        Task[] storage = new Task[MAX_TASKS]; //store the tasks

        //Generate data directory and make Vinbot.txt file
        FileEditor file = new FileEditor();
        file.makeFile();

        //Record user Input
        LineReader read = new LineReader(true);

        //Scan User Input
        read.scanMessage(true, format, storage);

        //Say bye
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static printWelcomeMessage getMessage() {
        String name = "Vinbot ^-^";
        String emptyLine = "----<<<<####****####>>>>----";
        String starLine = "****************************";
        String spacing = "    ";
        System.out.println(spacing + "Hello! I'm " + name);
        System.out.println(spacing + "What can I do for you?");
        System.out.println(spacing + "Please enter your current tasks");
        System.out.println(spacing + "Enter \"help\" for a list of available commands");
        System.out.print(spacing + emptyLine + "\n");
        return new printWelcomeMessage(emptyLine, starLine, spacing);
    }

    public static void printHelpMessage(printWelcomeMessage format) {
        System.out.println(format.spacing() + "Enter \"list\" to view your current tasks");
        System.out.println(format.spacing() + "Enter \"mark\" followed by a number to mark task as complete");
        System.out.println(format.spacing() + "To enter a task, enter \"todo\" followed by your task to store task");
        System.out.println(format.spacing() + "To enter a deadline, enter \"deadline\" followed by your task and \"/by\" due date to store deadline");
        System.out.println(format.spacing() + "To enter an event, enter \"event\" followed by your event and \"/from\" start date \"/to\" end date to store event");
        System.out.print(format.spacing() + "Enter \"unmark\" followed by a number to mark task as incomplete\n");
        System.out.print(format.spacing() + format.emptyLine() + "\n");
    }

    public record printWelcomeMessage(String emptyLine, String starLine, String spacing) {
    }
}
