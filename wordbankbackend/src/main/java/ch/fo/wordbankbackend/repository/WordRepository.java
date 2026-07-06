package ch.fo.wordbankbackend.repository;

import ch.fo.wordbankbackend.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenbank-Zugriff für {@link Word}-Objekte.
 *
 * Erbt von {@link JpaRepository} und erhält dadurch automatisch Methoden
 * wie save(), findById(), findAll(), count() und delete()
 */

public interface WordRepository extends JpaRepository<Word, String> {
    /**
     * Sucht und returniert alle Wörter, die favorisiert wurden.
     * @param isFavoriteWord Boolean-Wert, ob
     * @return
     */
    List<Word> findByIsFavoriteWord(boolean isFavoriteWord);
}
