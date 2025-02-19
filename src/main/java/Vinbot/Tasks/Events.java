package Vinbot.Tasks;

import Vinbot.Vinbot;

public class Events extends Task {
    private String start;
    private String end;
    public Events(String description, String start, String end) {
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
        if (!line.contains("/from") || !line.contains("to")) { //Remove invalid cases
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "invalid event not added >.<\n" + format.spacing() + format.starLine() + "\n");
        }
        String desc = line.substring(0, line.indexOf("/from"));
        if (desc.trim().isEmpty()) {
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "Description of event not found!! 0.o Please enter a start date after /from\n" + format.spacing() + format.starLine() + "\n");
        }
        String start = line.substring(line.indexOf("/from") + 5, line.indexOf("/to") - 1).trim();
        if (start.trim().isEmpty()) {
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "Start date of event not found!! 0.o Please enter a start date after /from\n" + format.spacing() + format.starLine() + "\n");
        }
        String end = line.substring(line.indexOf("/to") + 3).trim();
        if (end.trim().isEmpty()) {
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "End date of event not found!! 0.o Please enter an end date after /to\n" + format.spacing() + format.starLine() + "\n");
        }
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + desc + "(from: " + start + " to: " + end + ")");
        System.out.println(format.spacing() + format.starLine());
        return new String[]{desc, start, end};
    }
}
