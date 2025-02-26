package Vinbot.Tasks;

import Vinbot.Vinbot;
import Vinbot.MessageFormat;

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
        System.out.println(MessageFormat.getSpacing() + MessageFormat.getEmptyLine());
        System.out.println(MessageFormat.getSpacing() + "added: " + line);
        System.out.println(MessageFormat.getSpacing() + MessageFormat.getStarLine());
    }
}
