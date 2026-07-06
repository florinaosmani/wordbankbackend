package ch.fo.wordbankbackend.dto;

import java.util.List;

/**
 * Datentransfer-Objekt für das Lesen der Wörter.
 */
public record WordResponseDTO(
        String id,
        String word,
        boolean isFavoriteWord,
        int syllables,
        String pronunciation,
        List<DefinitionResponseDTO> results
) {
}
