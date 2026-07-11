package ch.fo.wordbankbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Datentransfer Objekt für das Erstellen und Ändern eines Wortes.
 * @param word Das Wort als String, darf nicht leer sein.
 * @param isFavoriteWord Ob das Wort favorisiert ist, defaults zu {@code false}.
 * @param syllables Die Anzahl der Silben, default zu {@code 0}.
 * @param pronunciation Die Aussprache des Wortes, darf nicht leer sein.
 * @param results Liste der Definitionen, muss mindestens eine Definition enthalten.
 */

public record WordFormDTO(
        @NotBlank(message = "must not be blank.")
        String word,

        //kein Validation, weil defaults to true bei boolean oder 0 bei int
        boolean isFavoriteWord,
        int syllables,

        @NotBlank(message = "must not be blank.")
        String pronunciation,

        @NotNull
        @Valid
        @Size(min = 1, message = "Word needs at least one definition.")
        List<DefinitionFormDTO> results
) {
}
