package ch.fo.wordbankbackend.controller;

import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService){
        this.wordService = wordService;
    }

    /**
     * Gibt alle Wörter zurück.
     * @return Alle Wörter als DTO.
     */
    @GetMapping("/api/words")
    public List<WordResponseDTO> getWords() {
        return wordService.getAllWords();
    }

    /**
     * Gibt ein Wort mit der gewünschten ID zurück.
     * @param id ID des gewünschten Wortes.
     * @return Wort mit der gewünschten ID als DTO.
     */
    @GetMapping("/api/words/{id}")
    public WordResponseDTO getWordById(@PathVariable String id){
        return wordService.getWordById(id);
    }

    /**
     * Gibt entweder alle favorisierten oder alle nicht favorisierten Wörter zurück.
     * @param isF Boolean, ob favorisierte oder nicht favorisierte Wörter zurückgegeben werden sollten.
     * @return Liste an entweder favorisierten oder nicht favorisierten Wörtern.
     */
    @GetMapping("/api/words/isFavWord/{isF}")
    public List<WordResponseDTO> getWordByFavorite(@PathVariable boolean isF){
        return wordService.getWordsByFavorite(isF);
    }

    /**
     * Erstellt ein neues Wort.
     * @param form Das Wort, welches man erstellen möchte.
     * @return Das neu erstellte Word.
     */
    @PostMapping("/api/words")
    @ResponseStatus(HttpStatus.CREATED)
    public Word createWord(@RequestBody WordFormDTO form){
        return wordService.createWord(form);
    }

    /**
     * Das gewünschte Wort aktualisieren.
     * @param id ID des zu aktualisierenden Wortes.
     * @param form Die aktualisierte Version des Wortes.
     * @return Das aktualisierte Wort.
     */
    @PutMapping("/api/words/{id}")
    public Word updateWord(@PathVariable String id, @RequestBody WordFormDTO form){
        return wordService.updateWord(id, form);
    }

    /**
     * Löscht das gewünschte Wort.
     * @param id ID des zu löschenden Wortes.
     */
    @DeleteMapping("/api/words/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWord(@PathVariable String id){
        wordService.deleteWord(id);
    }
}
