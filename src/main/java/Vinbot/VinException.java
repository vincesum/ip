package Vinbot;
import Vinbot.MessageFormat;

public class VinException extends Exception {
    public VinException(String message) {
        super(MessageFormat.getSpacing() + MessageFormat.getEmptyLine() + "\n" + MessageFormat.getSpacing() + message + "\n" + MessageFormat.getSpacing() + MessageFormat.getStarLine());

    }
}
