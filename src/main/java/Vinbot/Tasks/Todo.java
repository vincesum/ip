package Vinbot.Tasks;

import Vinbot.Vinbot;

public class Todo extends Task {
    protected static final String label = "T";
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getLabel() {
        return "T";
    }

    public String getDate() {
        return "";
    }

    public void scan(Vinbot.printWelcomeMessage format, String line) {
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + line);
        System.out.println(format.spacing() + format.starLine());
    }
}
