package Vinbot;

import Vinbot.Tasks.Parser;
import Vinbot.Tasks.Task;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vinbot {

    public static final Scanner in = new Scanner(System.in);
    public static int numberOfElements = 0;

    public Vinbot() throws FileNotFoundException {
    }

    public static void main(String[] args) {

        //Initialisation procedure
        MessageFormat format = new MessageFormat();
        format.printWelcomeMessage();

        //Storage
        ArrayList<Task> storage = new ArrayList<>();

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
                numberOfElements = read.scanMessage(true, format, storage, numberOfElements, fileIn);
            }
            catch (FileNotFoundException e) {
                System.out.println("No previous save detected");
            }
        }

        //Restore output stream
        disabler.restoreOutput();

        //Scan User Input
        read.scanMessage(true, format, storage,numberOfElements, in);

        //Say bye
        System.out.println("Bye. Hope to see you again soon!");
    }





}
