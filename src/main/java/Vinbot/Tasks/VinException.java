package Vinbot.Tasks;

public class VinException extends Exception {
    private String message;
    public VinException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
