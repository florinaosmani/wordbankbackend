package ch.fo.wordbankbackend.exception;

import ch.fo.wordbankbackend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Globaler Exception-Handler für die gesamte API.
 * Fängt bekannte Exceptions ab und gibt einheitliche {@link ErrorResponse}-Objekte zurück.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    /**
     * Behandelt ein nicht gefundenes Wort und liefert {@code 404}.
     * @param ex Die geworfene {@link WordNotFoundException}.
     * @return {@link ErrorResponse} mit dem jetztigen Datum, dem Status {@code 404} und der Fehlermeldung.
     */
    @ExceptionHandler(WordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(WordNotFoundException ex){
        return new ErrorResponse(
                LocalDateTime.now(),
                404,
                ex.getMessage(),
                Map.of()
                );
    }

    /**
     * Behandelt eine fehlgeschlagene Validierung und liefert {@code 400}.
     * Sammelt alle Felderfehler und gibt sie als Map zurück.
     *
     * @param ex Die geworfene {@link MethodArgumentNotValidException}.
     * @return {@link ErrorResponse} mit der aktuellen Zeit, Status {@code 400} und einer Map der fehlerhaften Fehler und deren Fehlermeldung.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return new ErrorResponse(
                LocalDateTime.now(),
                400,
                "Validation failed!",
                fieldErrors
        );
    }
}
