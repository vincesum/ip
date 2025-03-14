package Vinbot;

import Vinbot.Tasks.Task;
import Vinbot.Tasks.TaskList;

/** UI class to print outputs */
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

    /**
     * Prints message by following standard format.
     * @param line A line of text to be printed.
     */
    public static void printLine(String line) {
        System.out.println(spacing + emptyLine);
        System.out.println(spacing + line);
        System.out.println(spacing + starLine);
    }

    /** Prints newline by following standard format */
    public static void newLine() {
        System.out.println(UI.getSpacing() + UI.getStarLine());
    }

    /**
     * Prints exception errors
     * @param message An error message to be printed.
     * */
    public static void showError(String message) {
        System.out.println(spacing + emptyLine + "\n" + spacing + message + "\n" + spacing + starLine);
    }
    /** Prints task details based on task index provided
     * @param task A task object to be printed.
     * @param i Task index of task to be printed.
     */
    public static void printTask(Task task, int i) {
        System.out.println(spacing + task.toString(i, i));
    }
    /** Prints task details based on current index and task index.
     * @param task A task object to be printed.
     * @param i Current index of task to be printed.
     * @param index Task index of task to be printed.
     * */
    public static void printFindTask(Task task, int i, int index) {
        System.out.println(spacing + task.toString(i, index));
    }

    /** Prints welcome message. */
    public static void printWelcomeMessage() {
        System.out.println(spacing + "Hello! I'm " + name);
        System.out.println(spacing + "What can I do for you?");
        System.out.println(spacing + "Please enter your current tasks");
        System.out.println(spacing + "Enter \"help\" for a list of available commands");
        System.out.print(spacing + emptyLine + "\n");
    }

    /** Prints help message. */
    public static void printHelpMessage() {
        System.out.println(spacing + "Enter \"list\" to view your current tasks");
        System.out.println(spacing + "Enter \"mark\" followed by a task index to mark task as complete");
        System.out.print(spacing + "Enter \"unmark\" followed by a task index to mark task as incomplete\n");
        System.out.println(spacing + "To enter a task, enter \"todo\" followed by your task to store task");
        System.out.println(spacing + "To enter a deadline, enter \"deadline\" followed by your task and \"/by\" due date to store deadline");
        System.out.println(spacing + "To enter an event, enter \"event\" followed by your event and \"/from\" start date \"/to\" end date to store event");
        System.out.println(spacing + "To delete a task, enter \"delete\" followed by your task index to delete task");
        System.out.println(spacing + "To find a task, enter \"find\" followed by your task to find task");
        System.out.print(spacing + emptyLine + "\n");
    }

    /** Prints goodbye message. */
    public static void printGoodByeMessage() {
        printLine("Bye. Hope to see you again soon!");
    }
}
