package ch.fo.wordbankbackend.service;

import ch.fo.wordbankbackend.model.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WordServiceTest {

    @Autowired
    private WordService wordService;

    @Test
    @DisplayName("Gibt alle Wörter zurück")
    void getAllWordsReturnsData() {
        List<Word> words = wordService.getAllWords();
        assertFalse(words.isEmpty());
    }

    @Test
    @DisplayName("Gibt das korrekte Wort zurück, wenn die ID \"1\" ist")
    void getWordByIdReturnsCorrectWord() {
        Word word = wordService.getWordById("1");
        assertNotNull(word);
        assertEquals("1", word.getId());
    }

    @Test
    @DisplayName("Gibt die korrekte Anzahl an Wörtern zurück")
    void getAllWordsReturnsCorrectAmountOfWords(){
        long count = wordService.getAllWords().stream().count();

        assertEquals(2, count);
    }
}
