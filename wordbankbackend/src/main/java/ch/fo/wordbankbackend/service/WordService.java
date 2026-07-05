package ch.fo.wordbankbackend.service;

import ch.fo.wordbankbackend.model.Definition;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * Liefert alle Wörter aus der DB, samt deren Definitionen.
     * @return Liste mit allen Wörtern.
     */
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    /**
     * Sucht ein Wort anhand seiner ID und gibt es zurück.
     * @param id Die Id des gesuchten Wortes.
     * @return Das Wort mit der passenden ID, oder null, wenn nicht gefunden.
     */
    public Word getWordById(String id) {
        return wordRepository.findById(id).orElse(null);
    }

    /**
     * Speichert ein neues Wort in der DB.
     * @param word Das zu speichernde Wort.
     * @return Das in der DB gespeicherte Wort.
     */
    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    /**
     * Aktualisiert ein bestehendes Wort.
     * @param id Die ID des zu verändernde Wort.
     * @param word Daso Wort mit den Änderungen.
     * @return Das in der DB aktualisierte Wort.
     */
    public Word updateWord(String id, Word word){
        word.setId(id);
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
