package ch.fo.wordbankbackend;

import ch.fo.wordbankbackend.model.Word;
import ch.fo.wordbankbackend.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

/**
 * Füllt die Datenbank beim Programmstart mit Wörtern aus {@code words.json},
 * aber nur wenn die Tabelle noch leer ist.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final WordRepository wordRepository;
    private final ObjectMapper objectMapper;

    public DataSeeder(WordRepository wordRepository, ObjectMapper objectMapper){
        this.wordRepository = wordRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Liest {@code words.json} aus dem Classpath,
     * setzt den Backlink von {@link ch.fo.wordbankbackend.model.Definition} zu
     * {@link Word} und speichert alle Wörter in der DB.
     * Wird nur ausgeführt, wenn die DB leer ist, um doppeltes Seeding zu vermeiden.
     * @param args incoming main method arguments
     * @throws Exception wenn {@code words.json} nicht gefunden oder nicht gelesen werden kann.
     */
    @Override
    public void run(String... args) throws Exception{
        if(wordRepository.count() == 0){
            ClassPathResource resource = new ClassPathResource("words.json");

            try(InputStream inputStream = resource.getInputStream()) {
                List<Word> words = objectMapper.readValue(inputStream, new TypeReference<List<Word>>() {});

                words.forEach(word -> word.getResults().forEach(def -> def.setWord(word)));

                wordRepository.saveAll(words);
                System.out.println("DataSeeder: " + wordRepository.count() + " Wörter in die DB geschrieben.");
            }
        } else {
            System.out.println("Data Seeder: DB enthält bereits Daten, kein Seeding nötig");
        }
    }
}
