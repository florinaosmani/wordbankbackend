package ch.fo.wordbankbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

/**
 * Datentransfer-Objekt für das Erstellen und Ändern einer Definition.
 * @param definition
 * @param partOfSpeech
 * @param isFavoriteDefinition
 * @param note
 * @param synonyms
 * @param examples
 * @param antonyms
 */
public record DefinitionFormDTO(
        @NotBlank(message = "must not be blank.")
        String definition,

        @NotBlank
        @Pattern(regexp = "noun|verb|adjective|adverb|pronoun|preposition|conjunction|interjection",
        message = "Only parts of speech are allowed. Try: " +
                "noun, verb, adjective, adverb, pronoun, preposition, conjunction or interjection")
        String partOfSpeech,

        //leer, weil anscheinend defaults to false, wenn leer?
        boolean isFavoriteDefinition,

        String note,

        //Die dürfen leer sein.
        List<String> synonyms,
        List<String> examples,
        List<String> antonyms
) {
    /**
     * Kompakter Konstruktur, mit KI entwickelt, damit note und Listen nicht null,
     * sondern "" und [] leerer Array respektive sind.
     * @param definition
     * @param partOfSpeech
     * @param isFavoriteDefinition
     * @param note
     * @param synonyms
     * @param examples
     * @param antonyms
     */
    public DefinitionFormDTO {
        note = note != null ? note : "";
        synonyms = synonyms != null ? synonyms : List.of();
        examples = examples != null ? examples : List.of();
        antonyms = antonyms != null ? antonyms : List.of();
    }
}
