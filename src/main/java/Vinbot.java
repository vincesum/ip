import com.sun.security.auth.UnixNumericGroupPrincipal;

import java.util.Scanner;

public class Vinbot {
    public static void main(String[] args) {
        boolean isActive = true;    //Defines the operation status of the bot
        //Initialisation procedure
        String name = "Vinbot ^-^";
        String emptyLine = "----<<<<####****####>>>>----";
        String starLine = "****************************";
        System.out.println("    Hello! I'm " + name);
        System.out.println("    " + emptyLine);
        System.out.print("    What can I do for you?\n" + "    " + emptyLine + "\n");
        //Storage
        String[] storage = new String[100];
        int numberOfElements = 0;
        //Echo
        Scanner in = new Scanner(System.in);
        while (isActive) {
            String line;
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")) {    //exit loop
                isActive = false;
                break;
            }
            if (line.toLowerCase().equals("list")) {    //displays list in array
                int i = 0;
                while (storage[i] != null) {
                    System.out.println(i + 1 + ") " + storage[i]);
                    i++;
                }
            }
            else {
                storage[numberOfElements] = line;
                numberOfElements++;
                System.out.println("    " + emptyLine);
                System.out.println("    " + "added: "+ line);
                System.out.println("    " + starLine);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
