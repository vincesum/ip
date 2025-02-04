public class Deadlines extends Task {
    protected static final String label = "D";
    private String by;
    public Deadlines(String description, String date) {
        super(description);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    protected String getLabel() {
        return "D";
    }
}
