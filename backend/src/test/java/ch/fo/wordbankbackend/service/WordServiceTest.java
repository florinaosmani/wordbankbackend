package ch.fo.wordbankbackend.service;

import ch.fo.wordbankbackend.dto.DefinitionFormDTO;
import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.repository.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WordServiceTest {

    @Autowired
    private WordService wordService;
    @Autowired
    private WordRepository wordRepository;

    //aus SQ
    @Test
    @DisplayName("Gibt alle Wörter zurück.")
    void getAllWordsReturnsData() {
        List<WordResponseDTO> words = wordService.getAllWords();
        assertFalse(words.isEmpty());
    }

    //aus SQ
    @Test
    @DisplayName("Gibt das korrekte Wort zurück, wenn die ID \"1\" ist.")
    void getWordByIdReturnsCorrectWord() {
        WordResponseDTO word = wordService.getWordById("1");
        assertNotNull(word);
        assertEquals("1", word.id());
    }

    //aus SQ
    @Test
    @DisplayName("Ein neues Wort wird persistent in der DB gespeichert.")
    @Transactional
    void createWordPersistsToDb() {
        DefinitionFormDTO defForm = new DefinitionFormDTO(
                "greeting",
                "noun",
                false,
                "im a note",
                List.of("s1", "s2"),
                List.of("e1", "e2"),
                List.of("a1", "a2")
        );

        WordFormDTO form = new WordFormDTO(
                "hallo",
                false,
                1,
                "hallo",
                List.of(defForm)
        );

        WordResponseDTO saved = wordService.createWord(form);

        assertNotNull(saved.id());
        assertTrue(wordRepository.findById(saved.id()).isPresent());

    }

    //aus SQ
    @Test
    @DisplayName("Ein bestehendes Wort wird persistent aktualisiert")
    @Transactional
    void updateWordChangesExistingWord(){
        DefinitionFormDTO defForm = new DefinitionFormDTO(
                "greeting",
                "noun",
                false,
                "im a note",
                List.of("s1", "s2"),
                List.of("e1", "e2"),
                List.of("a1", "a2")
        );

        WordFormDTO form = new WordFormDTO(
                "hallo",
                false,
                1,
                "hallo",
                List.of(defForm)
        );

        WordResponseDTO saved = wordService.createWord(form);

        WordFormDTO toUpdate = new WordFormDTO(
                "byebye",
                false,
                1,
                "byebye",
                List.of(defForm)
        );

        WordResponseDTO updated = wordService.updateWord(saved.id(), toUpdate);

        assertEquals("byebye", wordRepository.findById(updated.id()).orElse(null).getWord());
    }
}
