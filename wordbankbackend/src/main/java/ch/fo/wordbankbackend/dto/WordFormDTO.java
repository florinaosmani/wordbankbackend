package ch.fo.wordbankbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Datentransfer Objekt für das Erstellen und Ändern eines Wortes.
 * @param word
 * @param isFavoriteWord
 * @param syllables
 * @param pronunciation
 * @param results
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
