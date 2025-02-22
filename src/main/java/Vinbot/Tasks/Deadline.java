package Vinbot.Tasks;

import Vinbot.Vinbot;

public class Deadline extends Task {
    private String by;
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

    public static String[] scan(Vinbot.printWelcomeMessage format, String line) throws VinException {
        if (!line.contains("/by")) { //Remove invalid cases
            throw new VinException("invalid deadline not added >.<", format);
        }
        String desc = line.substring(0, line.indexOf("/by"));
        if (desc.trim().isEmpty()) {
            throw new VinException("No description entered for deadline!!!", format);
        }
        String date = line.substring(line.indexOf("/by") + 3).trim();
        if (date.trim().isEmpty()) {
            throw new VinException("No date entered for deadline!!!", format);
        }
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + desc + "(by: " + date + ")");
        System.out.println(format.spacing() + format.starLine());
        return new String[]{desc, date};
    }
}
