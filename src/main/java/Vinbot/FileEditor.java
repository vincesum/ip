package Vinbot;

import Vinbot.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileEditor {
    public Path filePath = Paths.get("data", "Vinbot.txt");
    public boolean fileExists = java.nio.file.Files.exists(filePath);

    public Scanner readFile() throws FileNotFoundException {
        File file = new File("data/Vinbot.txt");
        Scanner in = new Scanner(file);
        return in;
    }

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
    public void writeToFile(ArrayList<Task> storage) throws IOException {
        FileWriter fw = new FileWriter("data/Vinbot.txt");
        fw.write(String.valueOf(revertToText(storage)));
        fw.close();
    }

    public String revertToText(ArrayList<Task> storage) {
        int i = 0;
        StringBuilder text = new StringBuilder();
        while (storage.size() > i) {
            switch (storage.get(i).getLabel()) {
            case "T":
                text.append("todo ").append(storage.get(i).getDescription()).append("\n");
                break;
            case "D":
                text.append("deadline ").append(storage.get(i).getDescription()).append("/by ").append(storage.get(i).getBy()).append("\n");
                break;
            case "E":
                text.append("event ").append(storage.get(i).getDescription()).append("/from ").append(storage.get(i).getStart()).append(" /to ").append(storage.get(i).getEnd()).append("\n");
                break;
            }
            if (storage.get(i).getStatusIcon().equals("X")) {
                text.append("mark ").append(i + 1).append("\n");
            }
            i++;
        }
        return text.toString();
    }
}
