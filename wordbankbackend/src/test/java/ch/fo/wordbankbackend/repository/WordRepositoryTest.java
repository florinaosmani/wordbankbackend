package ch.fo.wordbankbackend.repository;

import ch.fo.wordbankbackend.model.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    void seederHasDbFilled() {
        long anzahl = wordRepository.count();
        assertTrue(anzahl > 0);
    }

    @Test
    void wordCanBeLoaded() {
        Optional<Word> word = wordRepository.findById("1");
        assertTrue(word.isPresent());
    }
}
