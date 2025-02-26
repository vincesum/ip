package Vinbot;

public class MessageFormat {
    private final String name;
    private final String emptyLine;
    private final String starLine;
    private final String spacing;

    MessageFormat() {
        name = "Vinbot ^-^";
        emptyLine = "----<<<<####****####>>>>----";
        starLine = "****************************";
        spacing = "    ";
    }

    public String getName() {
        return name;
    }

    public String getEmptyLine() {
        return emptyLine;
    }

    public String getStarLine() {
        return starLine;
    }

    public String getSpacing() {
        return spacing;
    }

    public void printWelcomeMessage() {
        System.out.println(spacing + "Hello! I'm " + name);
        System.out.println(spacing + "What can I do for you?");
        System.out.println(spacing + "Please enter your current tasks");
        System.out.println(spacing + "Enter \"help\" for a list of available commands");
        System.out.print(spacing + emptyLine + "\n");
    }

    public void printHelpMessage(MessageFormat format) {
        System.out.println(format.getSpacing() + "Enter \"list\" to view your current tasks");
        System.out.println(format.getSpacing() + "Enter \"mark\" followed by a number to mark task as complete");
        System.out.println(format.getSpacing() + "To enter a task, enter \"todo\" followed by your task to store task");
        System.out.println(format.getSpacing() + "To enter a deadline, enter \"deadline\" followed by your task and \"/by\" due date to store deadline");
        System.out.println(format.getSpacing() + "To enter an event, enter \"event\" followed by your event and \"/from\" start date \"/to\" end date to store event");
        System.out.print(format.getSpacing() + "Enter \"unmark\" followed by a number to mark task as incomplete\n");
        System.out.print(format.getSpacing() + format.getEmptyLine() + "\n");
    }
}
