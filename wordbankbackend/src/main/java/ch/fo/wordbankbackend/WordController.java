package ch.fo.wordbankbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {
    private List<Word> words = List.of(
            new Word(
                   "1",
                   "abundant",
                    false,
                    3,
                    "əˈbʌndənt",
                    List.of(
                            new Definition(
                                   "d1",
                                    "existing or available in large quantities; more than enough",
                                    "adjective",
                                    false,
                                    "",
                                    List.of("The region has abundant natural resources.",
                                            "There was abundant evidence to support the claim."),
                                    List.of("plentiful",
                                            "ample",
                                            "copious",
                                            "profuse",
                                            "lavish",
                                            "bountiful",
                                            "rich",
                                            "generous"),
                                    List.of("scarce",
                                            "rare",
                                            "sparse",
                                            "lacking",
                                            "insufficient",
                                            "meager")
                            )
                    )
            ),
            new Word (
                    "2",
                    "flourish",
                    false,
                    2,
                    "ˈflʌrɪʃ",
                    List.of(
                            new Definition(
                                    "d2",
                                    "to grow or develop in a healthy or vigorous way",
                                    "verb",
                                    false,
                                    "",
                                    List.of(
                                            "The business began to flourish after the new manager took over.",
                                            "Plants flourish in warm, humid conditions."
                                    ),
                                    List.of(
                                            "thrive",
                                            "prosper",
                                            "bloom",
                                            "blossom",
                                            "grow",
                                            "succeed",
                                            "burgeon"
                                    ),
                                    List.of(
                                            "wither",
                                            "decline",
                                            "fail",
                                            "struggle",
                                            "languish",
                                            "deteriorate"
                                    )
                            ),
                            new Definition(
                                    "d3",
                                    "a bold or extravagant gesture or action made especially to attract attention",
                                    "noun",
                                    false,
                                    "",
                                    List.of(
                                            "He signed his name with a dramatic flourish.",
                                            "She entered the room with a flourish."
                                    ),
                                    List.of(
                                            "gesture",
                                            "display",
                                            "show",
                                            "fanfare",
                                            "wave"
                                    ),
                                    List.of() //empty Array, this definition has no antonyms :'(
                            )
                    )
            )
    );

    @GetMapping("api/words")
    public List<Word> getWords() {
        return words;
    }

    @GetMapping("api/words/{id}")
    public Word getWordById(@PathVariable String id){
        for (Word word : words){
            if (word.getId().equals(id)){
                return word;
            }
        }
        return null;
    }
}
