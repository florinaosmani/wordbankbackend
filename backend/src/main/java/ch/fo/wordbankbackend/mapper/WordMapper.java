package ch.fo.wordbankbackend.mapper;

import ch.fo.wordbankbackend.dto.DefinitionFormDTO;
import ch.fo.wordbankbackend.dto.DefinitionResponseDTO;
import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.model.Definition;
import ch.fo.wordbankbackend.model.Word;

import java.util.List;

/**
 * Wandelt {@link Word}-Entities in Response-DTOs und FormDTOs in Entities um.
 * Statische Hilfsklasse.
 */
public class WordMapper {

    private WordMapper(){

    }

    /**
     * Wandelt ein {@link Word}-Entity in ein {@link WordResponseDTO} um, inklusive aller zugehörigen Definitionen.
     * @param word Die zu umwandelnde {@link Word}-Entity.
     * @return {@link WordResponseDTO} mit allen Feldern und Definitions-DTOs.
     */
    public static WordResponseDTO toDTO(Word word) {
        List<DefinitionResponseDTO> defDTOs = word.getResults().stream()
                .map(WordMapper::toDefDTO)
                .toList();

        return new WordResponseDTO(
                word.getId(),
                word.getWord(),
                word.isFavoriteWord(),
                word.getSyllables(),
                word.getPronunciation(),
                defDTOs
        );
    }

    /**
     * Wandelt eine {@link Definition}-Entity in ein {@link DefinitionResponseDTO} um.
     * @param def Die zu umwandelnde {@link Definition}-Entity.
     * @return Das Definitions-DTO.
     */
    public static DefinitionResponseDTO toDefDTO(Definition def){
        return new DefinitionResponseDTO(
                def.getDefinition(),
                def.getPartOfSpeech(),
                def.isFavoriteDefinition(),
                def.getNote(),
                def.getSynonyms(),
                def.getExamples(),
                def.getAntonyms()
        );
    }

    /**
     * Wandelt ein {@link WordFormDTO} in eine {@link Word}-Entity um.
     * Die ID wird separat übergeben, da sie beim Erstellen vom Server generiert wird.
     * @param id ID des Wortes.
     * @param dto Die validierten Formulardaten.
     * @return Eine vollständige {@link Word}-Entity.
     */
    public static Word toEntity(String id, WordFormDTO dto){
        Word word = new Word();
        word.setId(id);
        word.setWord(dto.word());
        word.setFavoriteWord(dto.isFavoriteWord());
        word.setSyllables(dto.syllables());
        word.setPronunciation(dto.pronunciation());

        dto.results().stream()
                .map(WordMapper::toDefEntity)
                .forEach(word::addDefinition);

        return word;
    }

    /**
     * Wandelt ein {@link DefinitionFormDTO} in eine {@link Definition}-Entity um.
     * Der Backlink zu {@link Word} wird nicht hier, sondern via {@link WordMapper#toEntity(String, WordFormDTO)} gesetzt.
     * @param defDto Die validierten Formulardaten der Definition.
     * @return Eine {@link Definition}-Entity ohne gesetzen Word-Backlink.
     */
    public static Definition toDefEntity(DefinitionFormDTO defDto){
        return new Definition(
                defDto.definition(),
                defDto.partOfSpeech(),
                defDto.isFavoriteDefinition(),
                defDto.note(),
                defDto.examples(),
                defDto.synonyms(),
                defDto.antonyms()
        );
    }

}
