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

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    /**
     * Behandelt ein nicht gefundenes Wort und liefert 404.
     * @param ex Die Exception WordNotFoundException.
     * @return Eine ErrorResponse Instanz mit dem jetztigen Datum, dem Status 404, der Nachricht der WordNotFoundException und einem leeren Map.
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
     * Behandelt eine fehlgeschlagene Validierung und liefert 400 Bad-Request.
     * @param ex Die MethodArgumentNotValidException.
     * @return Eine ErrorResponse-Instanz mit der aktuellen Zeit, Status 400, Einer Validation failed NAchricht und einer HashMap, welche das Feld und die dazugehörige Fehlermeldung zeigt.
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
