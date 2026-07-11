package ch.fo.wordbankbackend.repository;

import ch.fo.wordbankbackend.model.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WordRepositorySliceTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    @DisplayName("Repository sucht nach favorisierten Wörtern.")
    void findByIsFavoriteWordReturnsMatchingWords(){

        Word word = new Word(
                "1",
                "hello",
                true,
                3,
                "hello"
        );

        wordRepository.save(word);

        List<Word> result = wordRepository.findByIsFavoriteWord(true);

        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(Word::isFavoriteWord);

    }

    @Test
    @DisplayName("Suchen einer ungültigen ID gibt dir empty zurück.")
    void findByIdReturnsEmptyForUnknownId(){
        Optional<Word> result = wordRepository.findById("invalid-id");

        assertThat(result).isEmpty();
    }
}
