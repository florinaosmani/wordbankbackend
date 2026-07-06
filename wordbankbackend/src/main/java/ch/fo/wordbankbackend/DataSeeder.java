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
 * Füllt die Datenbank beim Programmstart mit Wörtern aus words.json,
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
     * Seedet die DB mit den Wörtern, aber nur wenn sie leer ist.
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) throws Exception{
        if(wordRepository.count() == 0){
            ClassPathResource resource = new ClassPathResource("words.json");

            try(InputStream inputStream = resource.getInputStream()) {
                List<Word> words = objectMapper.readValue(inputStream, new TypeReference<List<Word>>() {});

               words.forEach(word -> {
                            word.getResults().forEach(def -> def.setWord(word));
               });


                wordRepository.saveAll(words);
                System.out.println("DataSeeder: " + wordRepository.count() + " Wörter in die DB geschrieben.");
            }
        } else {
            System.out.println("Data Seeder: DB enthält bereits Daten, kein Seeding nötig");
        }
    }
}
