package ch.fo.wordbankbackend.model;

import jakarta.persistence.*;

import java.util.List;

/** Ein Wort mit einer oder mehreren Definitionen. */

@Entity
@Table(name = "words")
public class Word {
    /** Eindeutige ID des Wortes. */
    @Id
    private String id;

    /** Das eigentliche Wort als String. */
    private String word;

    /** Ob das Wort favorisiert ist oder nicht. */
    private boolean isFavoriteWord;

    /** Die Anzahl der Silben. */
    private int syllables;

    /** Die Aussprache des Wortes. */
    private String pronunciation;

    /** Liste von Definitionen. Wird auf "word" in "Definition" gemapped. */
    @OneToMany(mappedBy = "word",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Definition> results;

    /** Parameterloser Konstruktor, den JPA/Hibernate braucht */
    public Word(){

    }

    /** Konstruktor mit allen Parametern */
    public Word(String id, String word, boolean isFavoriteWord, int syllables, String pronunciation, List<Definition> results) {
        this.id = id;
        this.word = word;
        this.isFavoriteWord = isFavoriteWord;
        this.syllables = syllables;
        this.pronunciation = pronunciation;
        this.results = results;
    }

    /**
     * Fügt eine Definition hinzu und hält beide Seiten der Beziehung synchron.
     * @param def
     */
    public void addDefinition(Definition def){
        results.add(def);
        def.setWord(this);
    }

    //removeItem wird im Frontend zwar nicht benutzt, jedoch sicher sinnvoll die zu haben, falls man das Frontend erweitert.
    /**
     * Entfernt eine Definition vom Wort und hält beide Seiten der Beziehung syncrhon.
     * @param def
     */
    public void removeItem(Definition def){
        results.remove(def);
        def.setWord(null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isFavoriteWord() {
        return isFavoriteWord;
    }

    public void setFavoriteWord(boolean favoriteWord) {
        isFavoriteWord = favoriteWord;
    }

    public int getSyllables() {
        return syllables;
    }

    public void setSyllables(int syllables) {
        this.syllables = syllables;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public List<Definition> getResults() {
        return results;
    }

    public void setResults(List<Definition> results) {
        this.results = results;
    }
}
