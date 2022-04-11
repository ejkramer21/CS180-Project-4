/**
 * NoGradesFoundException
 * An exception to be thrown when no grades are found for a student
 * @author Ananya Seetharaman
 * @version April 10, 2022
 */
public class NoGradesFoundException extends Exception {
        NoGradesFoundException(String message) {
            super(message);
        }
}
