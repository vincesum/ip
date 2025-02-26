package Vinbot.Tasks;

import Vinbot.Vinbot;
import Vinbot.MessageFormat;
public class VinException extends Exception {
    public VinException(String message, MessageFormat format) {
        super(format.getSpacing() + format.getEmptyLine() + "\n" + format.getSpacing() + message + "\n" + format.getSpacing() + format.getStarLine());

    }
}
