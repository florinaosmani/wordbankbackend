package ch.fo.wordbankbackend.service;

import ch.fo.wordbankbackend.dto.WordFormDTO;
import ch.fo.wordbankbackend.dto.WordResponseDTO;
import ch.fo.wordbankbackend.mapper.WordMapper;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
     * @return Das Wort mit der passenden ID, oder null, wenn nicht gefunden.
     */
    @Transactional(readOnly = true)
    public WordResponseDTO getWordById(String id) {
        Word word = wordRepository.findById(id).orElse(null);

        return WordMapper.toDTO(word);
    }

    /**
     * Liefert alle Wörter, welche favorisiert wurden.
     * @param isF Boolean, ob es favorisiert ist oder nicht.
     * @return Eine Liste an Wörtern.
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
     * @return Das in der DB gespeicherte Wort.
     */
    public Word createWord(WordFormDTO form) {
        String id = UUID.randomUUID().toString();
        Word word = WordMapper.toEntity(id, form);
        return wordRepository.save(word);
    }

    /**
     * Aktualisiert ein bestehendes Wort.
     * @param id Die ID des zu verändernde Wort.
     * @param form Das Wort mit den Änderungen als DTO.
     * @return Das in der DB aktualisierte Wort.
     */
    public Word updateWord(String id, WordFormDTO form){
        Word word = WordMapper.toEntity(id, form);
        return wordRepository.save(word);
    }

    /**
     * Löscht ein Wort aus der DB.
     * @param id Die ID des zu löschenden Worts.
     */
    public void deleteWord(String id){
        wordRepository.deleteById(id);
    }

}
