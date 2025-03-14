package Vinbot;

import java.io.PrintStream;


/** Class to save output of System.out then disable System.out when needed */
public class SystemOutDisabler {
    PrintStream originalOut;
    public void saveOutput() {
        originalOut = System.out;
    }

    public void disableOutput() {
        System.setOut(new PrintStream(PrintStream.nullOutputStream()));
    }

    public void restoreOutput() {
        System.setOut(originalOut);
    }
}
