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

    public void scan(MessageFormat format, String line) {
        System.out.println(format.getSpacing() + format.getEmptyLine());
        System.out.println(format.getSpacing() + "added: " + line);
        System.out.println(format.getSpacing() + format.getStarLine());
    }
}
