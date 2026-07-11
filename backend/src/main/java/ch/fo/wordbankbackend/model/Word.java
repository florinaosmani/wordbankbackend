package ch.fo.wordbankbackend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Wort mit einer oder mehreren {@link Definition}en.
 * Besitzt die Beziehung zur {@link Definition}-Tabelle via {@code @OneToMany}.
 */
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
    private List<Definition> results = new ArrayList<>();

    /** Parameterloser Konstruktor, den JPA/Hibernate braucht */
    public Word(){

    }

    /**
     * Erstellt ein neues Wort ohne Definitionen.
     * Definitionen werden nachträglich via {@link #addDefinition(Definition)} hinzugefügt.
     *
     * @param id            Die eindeutige ID des Wortes.
     * @param word          Das Wort als String.
     * @param isFavoriteWord Ob das Wort favorisiert ist.
     * @param syllables     Die Anzahl der Silben.
     * @param pronunciation Die Aussprache des Wortes.
     */
    public Word(String id, String word, boolean isFavoriteWord, int syllables, String pronunciation) {
        this.id = id;
        this.word = word;
        this.isFavoriteWord = isFavoriteWord;
        this.syllables = syllables;
        this.pronunciation = pronunciation;
    }

    /**
     * Fügt eine {@link Definition} hinzu und hält beide Seiten der Beziehung synchron, setzt auch den Backlink.
     * @param def Die hinzuzufügende Definition.
     */
    public void addDefinition(Definition def){
        results.add(def);
        def.setWord(this);
    }

    //removeItem wird im Frontend zwar nicht benutzt, jedoch sicher sinnvoll die zu haben, falls man das Frontend erweitert.
    /**
     * Entfernt eine {@link Definition} vom Wort und hält beide Seiten der Beziehung synchron.
     * @param def Die zu entfernende Definiton.
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
