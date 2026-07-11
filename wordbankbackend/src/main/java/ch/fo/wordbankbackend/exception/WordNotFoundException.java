package ch.fo.wordbankbackend.exception;

/**
 * Wird geworfen, wenn kein Wort mit der gesuchten ID existiert.
 */
public class WordNotFoundException extends RuntimeException {

    /**
     * @param id Die ID, für die kein Wort gefunden wurde.
     */
    public WordNotFoundException(String id) {
        super("There's no word with id " + id + ".");
    }
}
