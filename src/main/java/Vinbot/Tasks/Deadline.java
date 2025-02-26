package Vinbot.Tasks;

import Vinbot.MessageFormat;
import Vinbot.VinException;

public class Deadline extends Task {
    private String by;
    private static final String SPLITTER = "/by";
    private static final int SPLITTER_LENGTH = SPLITTER.length();
    public Deadline(String description, String date) {
        super(description);
        this.by = date;
    }

    @Override
    public String getLabel() {
        return "D";
    }

    public String getBy() {
        return by;
    }

    public String getDate() {
        return "(by: " + by + ")";
    }

    public static String[] scan(String line) throws VinException {
        if (!line.contains(SPLITTER)) { //Remove invalid cases
            throw new VinException("invalid deadline not added >.<");
        }
        String desc = line.substring(0, line.indexOf("/by"));
        if (desc.trim().isEmpty()) {
            throw new VinException("No description entered for deadline!!!");
        }
        String date = line.substring(line.indexOf(SPLITTER) + SPLITTER_LENGTH).trim();
        if (date.trim().isEmpty()) {
            throw new VinException("No date entered for deadline!!!");
        }
        System.out.println(MessageFormat.getSpacing() + MessageFormat.getEmptyLine());
        System.out.println(MessageFormat.getSpacing() + "added: " + desc + "(by: " + date + ")");
        System.out.println(MessageFormat.getSpacing() + MessageFormat.getStarLine());
        return new String[]{desc, date};
    }
}
