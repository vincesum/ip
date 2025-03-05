package Vinbot.Tasks;

import Vinbot.Date;
import Vinbot.UI;
import Vinbot.VinException;

public class Deadline extends Task {
    private String by;
    private static final String SPLITTER = "/by";
    private static final int SPLITTER_LENGTH = SPLITTER.length();

    //Constructor
    public Deadline(String description, String date) {
        super(description.trim());
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
        Date newBy = new Date(by);
        return "(by: " + newBy.getDate() + ")";
    }

    //Returns a String array of size 2 which is broken based on position of /by
    public static String[] scan(String line) throws VinException {
        //Case where /by is not present
        if (!line.contains(SPLITTER)) {
            throw new VinException("invalid deadline not added >.<");
        }

        //Case where description is empty
        String desc = line.substring(0, line.indexOf("/by"));
        if (desc.trim().isEmpty()) {
            throw new VinException("No description entered for deadline!!!");
        }

        //Case where date is empty
        String rawDate = line.substring(line.indexOf(SPLITTER) + SPLITTER_LENGTH).trim();
        if (rawDate.trim().isEmpty()) {
            throw new VinException("No date entered for deadline!!!");
        }

        try {
            //Throws exception if date data is in wrong format
            Date date = new Date(rawDate.trim());
            //Add deadline and store date as rawDate data
            String output = "added: " + desc + " (by: " + date.getDate() + ")";
            UI.printLine(output);
            return new String[]{desc, rawDate};
        }
        catch (Exception e) {
            throw new VinException("Date does not follow format of DD/MM/YYYY!");
        }
    }
}
