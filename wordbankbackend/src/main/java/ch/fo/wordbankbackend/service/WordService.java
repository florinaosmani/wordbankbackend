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
     * @return Liste mit allen Wörtern
     */
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    /**
     * Sucht ein Wort anhand seiner ID und gibt es zurück.
     * @param id Die Id des gesuchten Wortes
     * @return Das Wort mit der passenden ID, oder null, wenn nicht gefunden
     */
    public Word getWordById(String id) {
        return wordRepository.findById(id).orElse(null);
    }
}
