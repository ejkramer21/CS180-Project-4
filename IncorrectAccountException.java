/**
 * IncorrectAccountException.java
 * an exception to be thrown when the wrong type of account is used for something
 *
 * @author ?
 * @version April 10, 2022
 */
public class IncorrectAccountException extends Exception {
    IncorrectAccountException(String message) {
        super(message);
    }
}
