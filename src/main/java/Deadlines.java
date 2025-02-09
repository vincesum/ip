public class Deadlines extends Task {
    private String by;
    public Deadlines(String description, String date) {
        super(description);
        this.by = date;
    }

    @Override
    protected String getLabel() {
        return "D";
    }

    protected String getDate() {
        return "(by: " + by + ")";
    }

    public static String[] scan(Vinbot.printWelcomeMessage format, String line) {
        if (!line.contains("/by")) { //Remove invalid cases
            System.out.println(format.spacing() + format.emptyLine());
            System.out.println(format.spacing() + "invalid deadline not added");
            System.out.println(format.spacing() + format.starLine());
            return null;
        }

        String desc = line.substring(0, line.indexOf("/by"));
        String date = line.substring(line.indexOf("/by") + 4);
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + desc + "(by: " + date + ")");
        System.out.println(format.spacing() + format.starLine());
        return new String[]{desc, date};
    }
}
