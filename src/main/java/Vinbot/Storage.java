package Vinbot;

import Vinbot.Tasks.Task;
import Vinbot.Tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/** Storage class to handle creation of data, storage of data and loading of data. */
public class Storage {
    public Path filePath = Paths.get("data", "Vinbot.txt");
    public boolean fileExists = java.nio.file.Files.exists(filePath);

    public Scanner readFile() throws FileNotFoundException {
        File file = new File("data/Vinbot.txt");
        return new Scanner(file);
    }

    /** Creates a file if file does not exist at data/Vinbot.txt */
    public void makeFile() {
        if (!fileExists) {
            File file = new File("data", "Vinbot.txt");
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            catch (IOException e) {
                System.err.println("An error occurred while creating the file.");
            }
        }
    }

    /**
     * Write to file data/Vinbot.txt in String format.
     * @param storage
     * */
    public void writeToFile(TaskList storage) throws IOException {
        FileWriter fw = new FileWriter("data/Vinbot.txt");
        fw.write(String.valueOf(revertToText(storage)));
        fw.close();
    }

    /**
     * Converts the tasks stored in storage back into command form to store inside Vinbot.txt
     * @param storage
     * @return
     */
    public String revertToText(TaskList storage) {
        int i = 0;
        StringBuilder text = new StringBuilder();
        try {
            while (storage.getNumberOfElements() > i) {
                switch (storage.getTask(i).getLabel()) {
                case "T":
                    text.append("todo ").append(storage.getTask(i).getDescription()).append("\n");
                    break;
                case "D":
                    text.append("deadline ").append(storage.getTask(i).getDescription()).append("/by ").append(storage.getTask(i).getBy()).append("\n");
                    break;
                case "E":
                    text.append("event ").append(storage.getTask(i).getDescription()).append("/from ").append(storage.getTask(i).getStart()).append(" /to ").append(storage.getTask(i).getEnd()).append("\n");
                    break;
                }
                if (storage.getTask(i).getStatusIcon().equals("X")) {
                    text.append("mark ").append(i + 1).append("\n");
                }
                i++;
            }
        }
        catch (Exception e) {
            UI.showError(e.getMessage());
        }
        return text.toString();
    }
}
