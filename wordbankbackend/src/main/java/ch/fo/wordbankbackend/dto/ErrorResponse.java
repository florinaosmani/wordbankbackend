package ch.fo.wordbankbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Einheitliches Format für Error-Antoworten der API.
 * @param timestamp Aktueller Zeitstempel.
 * @param status    HTTP-Status.
 * @param message   Fehlermeldung.
 * @param fieldErrors   Map mit Feldname und zugehöriger Fehlermeldung, nur bei Validierungsfehlern befüllt, sonst leer.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, String> fieldErrors
) {
}
