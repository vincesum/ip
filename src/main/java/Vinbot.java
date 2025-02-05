public class Vinbot {

    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        boolean isActive = true;    //Defines the operation status of the bot

        //Initialisation procedure
        printWelcomeMessage result = getPrintWelcomeMessage();

        //Storage
        Task[] storage = new Task[MAX_TASKS]; //store the tasks

        //Record user Input
        LineReader read = new LineReader(isActive);

        //Scan User Input
        read.scanMessage(isActive, result, storage);

        //Say bye
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static printWelcomeMessage getPrintWelcomeMessage() {
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
        return new printWelcomeMessage(emptyLine, starLine, spacing);
    }

    public record printWelcomeMessage(String emptyLine, String starLine, String spacing) {
    }
}
