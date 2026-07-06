package ch.fo.wordbankbackend.dto;

import java.util.List;

/**
 * Datentransfer-Objekt für das Erstellen und Ändern eines Wortes.
 */
public record WordFormDTO(
        String word,
        boolean isFavoriteWord,
        int syllables,
        String pronunciation,
        List<DefinitionFormDTO> results
) {
}
