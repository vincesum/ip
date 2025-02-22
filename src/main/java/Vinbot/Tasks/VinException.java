package Vinbot.Tasks;

import Vinbot.Vinbot;

public class VinException extends Exception {
    public VinException(String message, Vinbot.printWelcomeMessage format) {
        super(format.spacing() + format.emptyLine() + "\n" + format.spacing() + message + "\n" + format.spacing() + format.starLine());

    }
}
