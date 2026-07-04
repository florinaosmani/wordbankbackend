package ch.fo.wordbankbackend.repository;

import ch.fo.wordbankbackend.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Datenbank-Zugriff für {@link Word}-Objekte.
 *
 * Erbt von {@link JpaRepository} und erhält dadurch automatisch Methoden
 * wie save(), findById(), findAll(), count() und delete()
 */

public interface WordRepository extends JpaRepository<Word, String> {
}
