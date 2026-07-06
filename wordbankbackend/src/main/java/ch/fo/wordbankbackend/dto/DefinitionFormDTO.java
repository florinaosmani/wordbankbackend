package ch.fo.wordbankbackend.dto;

import java.util.List;

/**
 * Datentransfer-Objekt für das Erstellen und Ändern einer Definition.
 */
public record DefinitionFormDTO(
        String definition,
        String partOfSpeech,
        boolean isFavoriteDefinition,
        String note,
        List<String> synonyms,
        List<String> examples,
        List<String> antonyms
) {
}
