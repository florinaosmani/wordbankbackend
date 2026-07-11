package ch.fo.wordbankbackend.service;

import ch.fo.wordbankbackend.dto.DefinitionFormDTO;
import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.exception.WordNotFoundException;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.repository.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WordServiceMockTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    @Test
    @DisplayName("Ein Wort mit einer ungültigen ID suchen gibt einen Fehler.")
    void getWordByIdThrowsWhenNotFound(){
        given(wordRepository.findById("invalid")).willReturn(Optional.empty());

        assertThatThrownBy(() -> wordService.getWordById("invalid")).isInstanceOf(WordNotFoundException.class);
    }

    @Test
    @DisplayName("Ein Wort erstellen kreirt eine ID und wird gespeichert.")
    void createWordGeneratesIdAndSave(){
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

        given(wordRepository.save(any(Word.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        WordResponseDTO created = wordService.createWord(form);

        assertThat(created.id()).isNotBlank();

        ArgumentCaptor<Word> captor = ArgumentCaptor.forClass(Word.class);
        verify(wordRepository).save(captor.capture());
        assertThat(captor.getValue().getWord()).isEqualTo("hallo");
    }
}
