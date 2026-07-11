package ch.fo.wordbankbackend.service;

import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.exception.WordNotFoundException;
import ch.fo.wordbankbackend.mapper.WordMapper;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service für die Geschäftslogik rund um {@link Word}-Objekte.
 */
@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * Liefert alle Wörter aus der DB als DTO, samt deren Definitionen.
     * @return Liste mit aller {@link WordResponseDTO}s.
     */
    @Transactional(readOnly = true)
    public List<WordResponseDTO> getAllWords() {
        return wordRepository.findAll().stream()
                .map(WordMapper::toDTO)
                .toList();
    }

    /**
     * Sucht ein Wort anhand seiner ID und gibt es als DTO zurück.
     * @param id Die Id des gesuchten Wortes.
     * @return Das gefundene {@link WordResponseDTO}.
     * @throws WordNotFoundException wenn kein Wort mit dieser ID existiert.
     */
    @Transactional(readOnly = true)
    public WordResponseDTO getWordById(String id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new WordNotFoundException(id));

        return WordMapper.toDTO(word);
    }

    /**
     * Liefert alle Wörter, welche favorisiert oder nicht favorisiert sind.
     * @param isF {@code true} für favorisierte, {@code false} für nicht favorisierte Wörter.
     * @return Eine Liste der gefilterten {@link WordResponseDTO}s.
     */
    @Transactional(readOnly = true)
    public List<WordResponseDTO> getWordsByFavorite(boolean isF){
        return wordRepository.findByIsFavoriteWord(isF).stream()
                .map(WordMapper::toDTO)
                .toList();
    }

    /**
     * Erstellt ein neues Wort aus den Formulardaten und speichert es in der DB.
     * @param form Die validierten Eingabedaten des neuen Wortes.
     * @return Das gespeicherte {@link WordResponseDTO}.
     */
    public WordResponseDTO createWord(WordFormDTO form) {
        String id = UUID.randomUUID().toString();
        Word word = WordMapper.toEntity(id, form);
        Word saved = wordRepository.save(word);
        return WordMapper.toDTO(saved);
    }

    /**
     * Aktualisiert ein bestehendes Wort anhand seiner ID.
     * @param id Die ID des zu aktualisierenden Wortes.
     * @param form Die validierten, aktualisierten Daten des Wortes.
     * @return Das aktualisierte {@link WordResponseDTO}.
     * @throws WordNotFoundException wenn kein Wort mit dieser ID existiert.
     */
    public WordResponseDTO updateWord(String id, WordFormDTO form){
        if (!wordRepository.existsById(id)){
            throw new WordNotFoundException(id);
        }

        Word word = WordMapper.toEntity(id, form);
        Word saved = wordRepository.save(word);
        return WordMapper.toDTO(saved);
    }

    /**
     * Löscht ein Wort anhand seiner ID inklusive aller zugehörigen Definitionen.
     * @param id Die ID des zu löschenden Wortes.
     * @throws WordNotFoundException wenn kein Wort mit dieser ID existiert.
     */
    public void deleteWord(String id){
        if (!wordRepository.existsById(id)){
            throw new WordNotFoundException(id);
        }

        wordRepository.deleteById(id);
    }

}
