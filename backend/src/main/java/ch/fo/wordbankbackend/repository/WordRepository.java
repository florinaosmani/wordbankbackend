package ch.fo.wordbankbackend.repository;

import ch.fo.wordbankbackend.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenbank-Zugriff für {@link Word}-Objekte.
 *
 * Erbt von {@link JpaRepository} und erhält dadurch automatisch Methoden
 * wie {@code save()}, {@code findById()}, {@code findAll()}, {@code count()} und {@code delete()}.
 */

public interface WordRepository extends JpaRepository<Word, String> {
    /**
     * Sucht und returniert alle Wörter, die favorisiert oder nicht favorisiert sind.
     * @param isFavoriteWord {@code true} für favorisierte, {@code false} für nicht favorisierte Wörter.
     * @return Liste der gefilterten {@link Word}-Objekte, leer wenn keine gefunden.
     */
    List<Word> findByIsFavoriteWord(boolean isFavoriteWord);
}
