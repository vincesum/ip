package Vinbot.Tasks;

import Vinbot.Date;
import Vinbot.UI;
import Vinbot.VinException;

public class Event extends Task {
    private String start;
    private String end;
    private final static String FIRST_SPLITTER = "/from";
    private final static String SECOND_SPLITTER =  "/to";
    private final static int FIRST_SPLITTER_LENGTH = FIRST_SPLITTER.length();
    private final static int SECOND_SPLITTER_LENGTH = SECOND_SPLITTER.length();
    private final static int SPACE_LENGTH = 1;

    //Constructor
    public Event(String description, String start, String end) {
        super(description.trim());
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStart() {
        return start;
    }
    @Override
    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        //Return date data in specified format
        Date newStart = new Date(start);
        Date newEnd = new Date(end);
        return "[E]" + super.toString() + " (from: " + newStart.getDate() + " to: " + newEnd.getDate() + ")";
    }

    @Override
    public String getLabel() {
        return "E";
    }

    public String getDate() {
        Date newStart = new Date(start);
        Date newEnd = new Date(end);
        return "(from: " + newStart.getDate() + " to: " + newEnd.getDate() +  ")";
    }

    //Returns a String array of size 3 which is broken based on positions of /from and /to
    public static String[] scan(String line) throws Exception {
        //Case where /from or /to is not present
        if (!line.contains(FIRST_SPLITTER) || !line.contains(SECOND_SPLITTER)) {
            throw new VinException("invalid event not added >.<");
        }
        //Case where description is empty
        String desc = line.substring(0, line.indexOf(FIRST_SPLITTER));
        if (desc.trim().isEmpty()) {
            throw new VinException("Description of event not found!! 0.o Please enter a start date after /from");
        }


        //Case where start date is empty
        String rawStart = line.substring(line.indexOf(FIRST_SPLITTER) + FIRST_SPLITTER_LENGTH, line.indexOf(SECOND_SPLITTER) - SPACE_LENGTH).trim();
        if (rawStart.trim().isEmpty()) {
            throw new VinException("Start date of event not found!! 0.o Please enter a start date after /from");
        }

        //Case where end date is empty
        String rawEnd = line.substring(line.indexOf(SECOND_SPLITTER) + SECOND_SPLITTER_LENGTH).trim();
        if (rawEnd.trim().isEmpty()) {
            throw new VinException("End date of event not found!! 0.o Please enter an end date after /to");
        }

        try {
            //Checks if start and end date follows proper formatting
            Date start = new Date(rawStart.trim());
            Date end = new Date(rawEnd.trim());
            //Add event and store date data as raw data
            String output = "added: " + desc + "(from: " + start.getDate() + " to: " + end.getDate() + ")";
            UI.printLine(output);
            return new String[]{desc, rawStart, rawEnd};
        }
        catch (Exception e) {
            throw new VinException("/from date or /to date does not follow format of DD/MM/YYYY!");
        }
    }
}
