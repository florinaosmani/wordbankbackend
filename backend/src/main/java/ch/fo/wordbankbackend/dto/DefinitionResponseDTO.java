package ch.fo.wordbankbackend.dto;

import java.util.List;

/*
Dieser Record existiert der Vollständigkeithalber, man könnte auch DefinitionFormDTO verwenden,
jedoch bietet das hier einfachere Skalierung in der Zukunft.

Gibt die ID extra nicht zurück, weil die im Frontend nicht gebraucht wird.
 */

/**
 * Datentransfer-Objekt für das Lesen einer Definition.
 * @param definition Die eigentliche Definition des Wortes.
 * @param partOfSpeech Die Wortart.
 * @param isFavoriteDefinition Ob die Definition favorisiert ist.
 * @param note Notiz zum favorisierten Wort.
 * @param synonyms Liste von Synonymen.
 * @param examples Liste von Beispielsätzen.
 * @param antonyms Liste von Antonymen.
 */
public record DefinitionResponseDTO(
        String definition,
        String partOfSpeech,
        boolean isFavoriteDefinition,
        String note,
        List<String> synonyms,
        List<String> examples,
        List<String> antonyms
) {
}
