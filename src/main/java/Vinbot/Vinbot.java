package Vinbot;

import Vinbot.Tasks.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vinbot {

    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        //Initialisation procedure
        UI.printWelcomeMessage();

        //Storage
        TaskList tasklist = new TaskList();

        //Generate data directory and make Vinbot.txt file
        Storage file = new Storage();
        file.makeFile();

        //Record user Input
        Parser read = new Parser(true);

        //Save and disable output stream
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
                UI.showError("File not found");
            }
        }

        //Restore output stream
        disabler.restoreOutput();

        //Scan User Input
        read.scanMessage(true, tasklist, in);

        //Say bye
        UI.printGoodByeMessage();
    }

}
