package Vinbot.Tasks;

import Vinbot.Vinbot;

public class Todos extends Task {
    protected static final String label = "T";
    public Todos(String description) {
        super(description);
    }

    @Override
    protected String getLabel() {
        return "T";
    }

    protected String getDate() {
        return "";
    }

    public void scan(Vinbot.printWelcomeMessage format, String line) {
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + line);
        System.out.println(format.spacing() + format.starLine());
    }
}
