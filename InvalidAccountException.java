/**
 * InvalidAccountException
 * An exception to be thrown when an invalid account type is used
 * @author Joy Kramer
 * @version April 10, 2022
 */
public class InvalidAccountException extends Exception {
    InvalidAccountException(String message) {
        super(message);
    }
}
