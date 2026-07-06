package ch.fo.wordbankbackend.mapper;

import ch.fo.wordbankbackend.dto.DefinitionFormDTO;
import ch.fo.wordbankbackend.dto.DefinitionResponseDTO;
import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.model.Definition;
import ch.fo.wordbankbackend.model.Word;

import java.util.List;

/**
 * Wandelt DTOs in Entities und Entities in DTOs um.
 */
public class WordMapper {

    private WordMapper(){

    }

    /**
     * Wandelt ein Wort-Entity in ein DTO um.
     * @param word Die Wort-Entität.
     * @return Das Wort-DTO mit den Definition DTOs.
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
     * Wandelt eine Definitions-Entität in ein DTO um.
     * @param def Die Definitions-Entität.
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
     * Wandelt ein Wort DTO in eine Entität um.
     * @param id ID des Wortes.
     * @param dto Das DTO des Wortes.
     * @return Eine Wort-Entität.
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
     * Wandelt ein Definitions DTO in eine Entität um.
     * @param defDto Das DTO der Definition.
     * @return Eine Definitionsentität.
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
