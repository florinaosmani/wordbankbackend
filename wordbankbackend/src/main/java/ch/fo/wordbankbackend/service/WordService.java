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

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * Liefert alle Wörter aus der DB als DTO, samt deren Definitionen.
     * @return Liste mit allen Wörtern als DTO.
     */
    @Transactional(readOnly = true)
    public List<WordResponseDTO> getAllWords() {
        return wordRepository.findAll().stream()
                .map(WordMapper::toDTO)
                .toList();
    }

    /**
     * Sucht ein Wort anhand seiner ID und gibt es zurück.
     * @param id Die Id des gesuchten Wortes.
     * @return Das Wort mit der passenden ID als DTO, oder null, wenn nicht gefunden.
     */
    @Transactional(readOnly = true)
    public WordResponseDTO getWordById(String id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new WordNotFoundException(id));

        return WordMapper.toDTO(word);
    }

    /**
     * Liefert alle Wörter, welche favorisiert wurden.
     * @param isF Boolean, ob es favorisiert ist oder nicht.
     * @return Eine Liste an Wörtern als DTOs.
     */
    @Transactional(readOnly = true)
    public List<WordResponseDTO> getWordsByFavorite(boolean isF){
        return wordRepository.findByIsFavoriteWord(isF).stream()
                .map(WordMapper::toDTO)
                .toList();
    }

    /**
     * Speichert ein neues Wort in der DB.
     * @param form Das zu speichernde Wort als DTO.
     * @return Das in der DB gespeicherte Wort als DTO.
     */
    public WordResponseDTO createWord(WordFormDTO form) {
        String id = UUID.randomUUID().toString();
        Word word = WordMapper.toEntity(id, form);
        Word saved = wordRepository.save(word);
        return WordMapper.toDTO(saved);
    }

    /**
     * Aktualisiert ein bestehendes Wort.
     * @param id Die ID des zu verändernde Wort.
     * @param form Das Wort mit den Änderungen als DTO.
     * @return Das in der DB aktualisierte Wort als DTO.
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
     * Löscht ein Wort aus der DB.
     * @param id Die ID des zu löschenden Worts.
     */
    public void deleteWord(String id){
        if (!wordRepository.existsById(id)){
            throw new WordNotFoundException(id);
        }

        wordRepository.deleteById(id);
    }

}
