package Vinbot.Tasks;

import Vinbot.UI;

public class Todo extends Task {
    protected static final String label = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getLabel() {
        return "T";
    }

    public void scan(String line) {
        String output = "added: " + line;
        UI.printLine(output);
    }
}
