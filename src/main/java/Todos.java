public class Todos extends Task {
    protected static final String label = "T";
    public Todos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected String getLabel() {
        return "T";
    }

    protected String getDate() {
        return "";
    }

    public void scan(Vinbot.printWelcomeMessage result, String line) {
        System.out.println(result.spacing() + result.emptyLine());
        System.out.println(result.spacing() + "added: " + line);
        System.out.println(result.spacing() + result.starLine());
    }
}
