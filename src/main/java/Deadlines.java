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

    public static String[] scan(Vinbot.printWelcomeMessage format, String line) throws VinException {
        if (!line.contains("/by")) { //Remove invalid cases
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "invalid deadline not added >.<\n" + format.spacing() + format.starLine() + "\n");
        }
        String desc = line.substring(0, line.indexOf("/by"));
        if (desc.trim().isEmpty()) {
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "No description entered for deadline!!!\n" + format.spacing() + format.starLine() + "\n");
        }
        String date = line.substring(line.indexOf("/by") + 3).trim();
        if (date.trim().isEmpty()) {
            throw new VinException(format.spacing() + format.emptyLine() + "\n" + format.spacing() + "No date entered for deadline!!!\n" + format.spacing() + format.starLine() + "\n");
        }
        System.out.println(format.spacing() + format.emptyLine());
        System.out.println(format.spacing() + "added: " + desc + "(by: " + date + ")");
        System.out.println(format.spacing() + format.starLine());
        return new String[]{desc, date};
    }
}
