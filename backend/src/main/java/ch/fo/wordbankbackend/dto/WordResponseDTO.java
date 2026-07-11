package ch.fo.wordbankbackend.dto;

import java.util.List;

/**
 * Datentransfer-Objekt für das Lesen der  Wörter.
 * @param id Die eindeutige ID des Wortes.
 * @param word Das Wort als String.
 * @param isFavoriteWord Ob das Wort favorisiert ist.
 * @param syllables Die Anzahl der Silben.
 * @param pronunciation Die Aussprache des Wortes.
 * @param results Liste der Definitionen.
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
