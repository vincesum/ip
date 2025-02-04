public class Events extends Task {
    protected static final String label = "E";
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
}
