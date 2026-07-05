package ch.fo.wordbankbackend;

import ch.fo.wordbankbackend.model.Definition;
import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.repository.WordRepository;
import ch.fo.wordbankbackend.service.WordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Füllt die Datenbank beim Programmstart mit Startdaten.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final WordRepository wordRepository;

    public DataSeeder(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    /**
     * Seedet die DB mit den Wörtern, aber nur wenn sie leer ist.
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args){
        if(wordRepository.count() == 0){
            wordRepository.saveAll(getStartWords());
            System.out.println("DataSeeder: " + wordRepository.count() + " Wörter in die DB geschrieben.");
        } else {
            System.out.println("Data Seeder: DB enthält bereits Daten, kein Seeding nötig");
        }
    }

    /**
     * Liefert eine Liste aller Startwörter.
     * @return Liste aller Startwörter
     */
    private List<Word> getStartWords() {
        Word word1 = new Word(
                "1",
                "abundant",
                false,
                3,
                "əˈbʌndənt"
        );

        Definition def1 = new Definition(
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
        );

        word1.addDefinition(def1);

        Word word2 = new Word(
                "2",
                "flourish",
                false,
                2,
                "ˈflʌrɪʃ"
        );

        Definition def2 = new Definition(
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
        );

        Definition def3 = new Definition(
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
        );

        word2.addDefinition(def2);
        word2.addDefinition(def3);

        return List.of(word1, word2);
    }
}
