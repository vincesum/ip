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

    protected String getDate() {
        return "(by: " + by + ")";
    }

    public static String[] scan(Vinbot.printWelcomeMessage result, String line) {
        if (!line.contains("/by")) { //Remove invalid cases
            System.out.println(result.spacing() + result.emptyLine());
            System.out.println(result.spacing() + "invalid deadline not added");
            System.out.println(result.spacing() + result.starLine());
            return null;
        }
        String desc = line.substring(0 , line.indexOf("/by"));
        String date = line.substring(line.indexOf("/by") + 4);
        System.out.println(result.spacing() + result.emptyLine());
        System.out.println(result.spacing() + "added: " + desc + "(by: " + date + ")");
        System.out.println(result.spacing() + result.starLine());
        return new String[]{desc, date};
    }
}
