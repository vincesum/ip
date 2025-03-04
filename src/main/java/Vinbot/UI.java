package Vinbot;

import Vinbot.Tasks.Task;
import Vinbot.Tasks.TaskList;

public class UI {
    private static final String name = "Vinbot ^-^";
    private static final String emptyLine = "----<<<<####****####>>>>----";
    private static final String starLine = "****************************";
    private static final String spacing = "    ";

    public static String getName() {
        return name;
    }

    public static String getEmptyLine() {
        return emptyLine;
    }

    public static String getStarLine() {
        return starLine;
    }

    public static String getSpacing() {
        return spacing;
    }

    public static void printLine(String line) {
        System.out.println(spacing + emptyLine);
        System.out.println(spacing + line);
        System.out.println(spacing + starLine);
    }

    public static void newLine() {
        System.out.println(UI.getSpacing() + UI.getStarLine());
    }

    public static void showError(String message) {
        System.out.println(spacing + emptyLine + "\n" + spacing + message + "\n" + spacing + starLine);
    }
    //i determines the number allocated to task printed
    public static void printTask(Task task, int i) {
        System.out.println(spacing + task.toString(i, i));
    }
    //index determines the number allocated to task printed
    public static void printFindTask(Task task, int i, int index) {
        System.out.println(spacing + task.toString(i, index));
    }

    public static void printWelcomeMessage() {
        System.out.println(spacing + "Hello! I'm " + name);
        System.out.println(spacing + "What can I do for you?");
        System.out.println(spacing + "Please enter your current tasks");
        System.out.println(spacing + "Enter \"help\" for a list of available commands");
        System.out.print(spacing + emptyLine + "\n");
    }

    public static void printHelpMessage() {
        System.out.println(spacing + "Enter \"list\" to view your current tasks");
        System.out.println(spacing + "Enter \"mark\" followed by a number to mark task as complete");
        System.out.println(spacing + "To enter a task, enter \"todo\" followed by your task to store task");
        System.out.println(spacing + "To enter a deadline, enter \"deadline\" followed by your task and \"/by\" due date to store deadline");
        System.out.println(spacing + "To enter an event, enter \"event\" followed by your event and \"/from\" start date \"/to\" end date to store event");
        System.out.print(spacing + "Enter \"unmark\" followed by a number to mark task as incomplete\n");
        System.out.print(spacing + emptyLine + "\n");
    }

    public static void printGoodByeMessage() {
        printLine("Bye. Hope to see you again soon!");
    }
}
