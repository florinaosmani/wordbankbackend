package ch.fo.wordbankbackend.controller;

import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.service.WordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für Wort-Operationen.
 * Stellt Endpoints für das Abrufen, ERstellen, Aktualisieren und Löschen von Wörtern bereit.
 */
@RestController
@RequestMapping("/api/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService){
        this.wordService = wordService;
    }

    /**
     * Liefert alle Wörter aus der Datenbank zurück.
     * @return Liste aller {@link WordResponseDTO}s.
     */
    @GetMapping
    public List<WordResponseDTO> getWords() {
        return wordService.getAllWords();
    }

    /**
     * Liefert ein Wort anhand seiner ID.
     * @param id ID des gewünschten Wortes.
     * @return Das gefundene Wort als {@link WordResponseDTO}.
     * @throws ch.fo.wordbankbackend.exception.WordNotFoundException wenn kein Wort mit dieser ID existiert.
     */
    @GetMapping("/{id}")
    public WordResponseDTO getWordById(@PathVariable String id){
        return wordService.getWordById(id);
    }

    /**
     * Gibt entweder alle favorisierten oder alle nicht favorisierten Wörter zurück.
     * @param isF {@code true} für favorisierte, {@code false} für nicht favorisierte Wörter.
     * @return Liste an gefilterten {@link WordResponseDTO}
     */
    @GetMapping("/isFavWord/{isF}")
    public List<WordResponseDTO> getWordByFavorite(@PathVariable boolean isF){
        return wordService.getWordsByFavorite(isF);
    }

    /**
     * Erstellt ein neues Wort aus den übergebenen Formulardaten.
     * @param form Das Wort, welches man erstellen möchte.
     * @return Das neu erstellte Word als {@link WordResponseDTO}.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordResponseDTO createWord(@Valid @RequestBody WordFormDTO form){
        return wordService.createWord(form);
    }

    /**
     * Das gewünschte Wort aktualisieren anhand seiner ID.
     * @param id ID des zu aktualisierenden Wortes.
     * @param form Die aktualisierte, validierte Version des Wortes.
     * @return Das aktualisierte Wort als {@link WordResponseDTO}.
     * @throws ch.fo.wordbankbackend.exception.WordNotFoundException wenn kein Wort mit dieser ID existiert.
     */
    @PutMapping("/{id}")
    public WordResponseDTO updateWord(@PathVariable String id, @Valid @RequestBody WordFormDTO form){
        return wordService.updateWord(id, form);
    }

    /**
     * Löscht das gewünschte Wort anhand seiner ID inklusive aller zugehöriger Definitionen.
     * @param id ID des zu löschenden Wortes.
     * @throws ch.fo.wordbankbackend.exception.WordNotFoundException wenn kein Wort mit dieser ID existiert.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWord(@PathVariable String id){
        wordService.deleteWord(id);
    }
}
