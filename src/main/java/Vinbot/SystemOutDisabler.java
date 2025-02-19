package Vinbot;

import java.io.PrintStream;

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
