/**
 * QuizAlreadyExistsException
 *  An exception to be thrown when an attempt is made to create a quiz that already exists.
 *
 * @author Joy Kramer
 * @version April 10, 2022
 */
public class QuizAlreadyExistsException extends Exception {
    public QuizAlreadyExistsException(String message) {
        super(message);
    }
}
