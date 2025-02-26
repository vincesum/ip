package Vinbot;

import Vinbot.Tasks.Parser;
import Vinbot.Tasks.Task;
import Vinbot.Tasks.TaskList;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vinbot {

    public static final Scanner in = new Scanner(System.in);

    public Vinbot() throws FileNotFoundException {
    }

    public static void main(String[] args) {

        //Initialisation procedure
        MessageFormat.printWelcomeMessage();

        //Storage
        TaskList tasklist = new TaskList();

        //Generate data directory and make Vinbot.txt file
        Storage file = new Storage();
        file.makeFile();

        //Record user Input
        Parser read = new Parser(true);

        //Disabling output stream
        SystemOutDisabler disabler = new SystemOutDisabler();
        disabler.saveOutput();
        disabler.disableOutput();

        //Scan Saved Inputs
        Storage editor = new Storage();
        if (editor.fileExists) {
            try {
                Scanner fileIn = editor.readFile();
                read.scanMessage(true, tasklist, fileIn);
            }
            catch (FileNotFoundException e) {
                System.out.println("No previous save detected");
            }
        }

        //Restore output stream
        disabler.restoreOutput();

        //Scan User Input
        read.scanMessage(true, tasklist, in);

        //Say bye
        System.out.println("Bye. Hope to see you again soon!");
    }





}
