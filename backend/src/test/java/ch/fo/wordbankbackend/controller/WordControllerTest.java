package ch.fo.wordbankbackend.controller;

import ch.fo.wordbankbackend.dto.DefinitionResponseDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.exception.WordNotFoundException;
import ch.fo.wordbankbackend.service.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(WordController.class)
class WordControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private WordService service;

    @Test
    void getByIdReturns200WithBody(){
        DefinitionResponseDTO def = new DefinitionResponseDTO(
                "greeting",
                "noun",
                false,
                "im a note",
                List.of("s1", "s2"),
                List.of("e1", "e2"),
                List.of("a1", "a2")
        );

        WordResponseDTO word = new WordResponseDTO(
                "1",
                "hallo",
                false,
                2,
                "hallo",
                List.of(def)
        );

        given(service.getWordById("1")).willReturn(word);

        assertThat(mvc.get().uri("/api/words/1"))
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.word")
                .isEqualTo("hallo");
    }

    @Test
    @DisplayName("Ungültige ID aufrufen erzeugt einen Error.")
    void getByIdReturns404WhenServiceThrows(){
        given(service.getWordById("999")).willThrow(new WordNotFoundException(("999")));

        assertThat(mvc.get().uri("/api/words/999")).hasStatus(HttpStatus.NOT_FOUND);
    }
}
