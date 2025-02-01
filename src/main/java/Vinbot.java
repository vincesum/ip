import java.util.Scanner;

public class Vinbot {
    public static void main(String[] args) {
        boolean isActive = true;    //Defines the operation status of the bot
        //Initialisation procedure
        String name = "Vinbot ^-^";
        String emptyLine = "----<<<<####****####>>>>----";
        System.out.println("Hello! I'm " + name);
        System.out.println(emptyLine);
        System.out.print("What can I do for you?\n" + emptyLine + "\n");
        //Echo
        Scanner in = new Scanner(System.in);
        while (isActive) {
            String line;
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")) {
                isActive = false;
                break;
            }
            System.out.println(emptyLine);
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
