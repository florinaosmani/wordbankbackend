package ch.fo.wordbankbackend.controller;

import ch.fo.wordbankbackend.model.Definition;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.service.WordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService){
        this.wordService = wordService;
    }

    @GetMapping("api/words")
    public List<Word> getWords() {
        return wordService.getAllWords();
    }

    @GetMapping("api/words/{id}")
    public Word getWordById(@PathVariable String id){
        return wordService.getWordById(id);
    }
}
