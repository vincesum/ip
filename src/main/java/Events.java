public class Events extends Task {
    private String start;
    private String end;
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
    @Override
    protected String getLabel() {
        return "E";
    }
    protected String getDate() {
        return "(from: " + start + " to: " + end +  ")";
    }
    public static String[] scan(Vinbot.printWelcomeMessage format, String line) {
        if (!line.contains("/from") || !line.contains("/to")) { //Remove invalid cases
            System.out.println(format.spacing() + format.emptyLine());
            System.out.println(format.spacing() + "invalid event not added");
            System.out.println(format.spacing() + format.starLine());
            return null;
        }

        String desc = line.substring(0, line.indexOf("/from"));
        String start = line.substring(line.indexOf("/from") + 6, line.indexOf("/to") - 1);
        String end = line.substring(line.indexOf("/to") + 4);
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + desc + "(from: " + start + " to: " + end + ")");
        System.out.println(format.spacing() + format.starLine());
        return new String[]{desc, start, end};
    }
}
