package ch.fo.wordbankbackend;

import ch.fo.wordbankbackend.repository.WordRepository;
import ch.fo.wordbankbackend.service.WordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Füllt die Datenbank beim Programmstart mit Startdaten.
 */

@Component
public class DataSeeder implements CommandLineRunner {

    private final WordService wordService;
    private final WordRepository wordRepository;

    public DataSeeder(WordService wordService, WordRepository wordRepository){
        this.wordRepository = wordRepository;
        this.wordService = wordService;
    }

    /**
     * Seedet die DB mit den Wörtern, aber nur wenn sie leer ist.
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args){
        if(wordRepository.count() == 0){
            wordRepository.saveAll(wordService.getAllWords());
            System.out.println("DataSeeder: " + wordRepository.count() + " Wörter in die DB geschrieben.");
        } else {
            System.out.println("Data Seeder: DB enthält bereits Daten, kein Seeding nötig");
        }
    }
}
