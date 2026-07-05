package ch.fo.wordbankbackend.controller;

import ch.fo.wordbankbackend.model.Definition;
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
     * @return Alle Wörter.
     */
    @GetMapping("/api/words")
    public List<Word> getWords() {
        return wordService.getAllWords();
    }

    /**
     * Gibt ein Wort mit der gewünschten ID zurück.
     * @param id ID des gewünschten Wortes.
     * @return Wort mit der gewünschten ID.
     */
    @GetMapping("/api/words/{id}")
    public Word getWordById(@PathVariable String id){
        return wordService.getWordById(id);
    }

    /**
     * Erstellt ein neues Wort.
     * @param word Das Wort, welches man erstellen möchte.
     * @return Das neu erstellte Word.
     */
    @PostMapping("/api/words")
    @ResponseStatus(HttpStatus.CREATED)
    public Word createWord(@RequestBody Word word){
        return wordService.createWord(word);
    }

    /**
     * Das gewünschte Wort aktualisieren.
     * @param id ID des zu aktualisierenden Wortes.
     * @param word Die aktualisierte Version des Wortes.
     * @return Das aktualisierte Wort.
     */
    @PutMapping("/api/words/{id}")
    public Word updateWord(@PathVariable String id, @RequestBody Word word){
        return wordService.updateWord(id, word);
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
