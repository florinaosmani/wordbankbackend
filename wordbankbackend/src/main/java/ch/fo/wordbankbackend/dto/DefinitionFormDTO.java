package ch.fo.wordbankbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

/**
 * Datentransfer-Objekt für das Erstellen und Ändern einer Definition.
 * @param definition Die eigentliche Definition des Wortes, darf nicht leer sein.
 * @param partOfSpeech Die Wortart, erlaubte Werte: noun, verb, adjective, adverb, pronoun, preposition, conjunction, interjection.
 * @param isFavoriteDefinition Ob die Definition favorisiert ist, defaults zu {@code false}
 * @param note Optionale Notiz, defaults zu {@code ""}
 * @param synonyms Optionale Liste von Synonymen, defaults zu leerem Array
 * @param examples Optionale Liste von Beispielsätzen, defaults zu leerem Array
 * @param antonyms Optionale Liste von Antonymen, defaults zu leerem Array
 */
public record DefinitionFormDTO(
        @NotBlank(message = "must not be blank.")
        String definition,

        @NotBlank
        @Pattern(regexp = "noun|verb|adjective|adverb|pronoun|preposition|conjunction|interjection",
        message = "Only parts of speech are allowed. Try: " +
                "noun, verb, adjective, adverb, pronoun, preposition, conjunction or interjection")
        String partOfSpeech,

        //leer, weil anscheinend defaults to false, wenn leer
        boolean isFavoriteDefinition,

        String note,

        List<String> synonyms,
        List<String> examples,
        List<String> antonyms
) {
    /**
     * Setzt {@code null}-Werte für note und die Listen auf sinnvolle Defaults.
     */
    public DefinitionFormDTO {
        note = note != null ? note : "";
        synonyms = synonyms != null ? synonyms : List.of();
        examples = examples != null ? examples : List.of();
        antonyms = antonyms != null ? antonyms : List.of();
    }
}
