package Vinbot;

import java.io.PrintStream;


//Class to disable System.out
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
