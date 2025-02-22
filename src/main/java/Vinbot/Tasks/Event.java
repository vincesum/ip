package Vinbot.Tasks;

import Vinbot.Vinbot;

public class Event extends Task {
    private String start;
    private String end;
    private final static String FIRST_SPLITTER = "/from";
    private final static String SECOND_SPLITTER =  "/to";
    private final static int FIRST_SPLITTER_LENGTH = FIRST_SPLITTER.length();
    private final static int SECOND_SPLITTER_LENGTH = SECOND_SPLITTER.length();
    private final static int SPACE_LENGTH = 1;
    public Event(String description, String start, String end) {
        super(description);
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
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
    @Override
    public String getLabel() {
        return "E";
    }
    public String getDate() {
        return "(from: " + start + " to: " + end +  ")";
    }
    public static String[] scan(Vinbot.printWelcomeMessage format, String line) throws Exception {
        if (!line.contains(FIRST_SPLITTER) || !line.contains(SECOND_SPLITTER)) { //Remove invalid cases
            throw new VinException("invalid event not added >.<", format);
        }
        String desc = line.substring(0, line.indexOf(FIRST_SPLITTER));
        if (desc.trim().isEmpty()) {
            throw new VinException("Description of event not found!! 0.o Please enter a start date after /from", format);
        }
        String start = line.substring(line.indexOf(FIRST_SPLITTER) + FIRST_SPLITTER_LENGTH, line.indexOf(SECOND_SPLITTER) - SPACE_LENGTH).trim();
        if (start.trim().isEmpty()) {
            throw new VinException("Start date of event not found!! 0.o Please enter a start date after /from", format);
        }
        String end = line.substring(line.indexOf(SECOND_SPLITTER) + SECOND_SPLITTER_LENGTH).trim();
        if (end.trim().isEmpty()) {
            throw new VinException("End date of event not found!! 0.o Please enter an end date after /to", format);
        }
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + desc + "(from: " + start + " to: " + end + ")");
        System.out.println(format.spacing() + format.starLine());
        return new String[]{desc, start, end};
    }
}
