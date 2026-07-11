package ch.fo.wordbankbackend.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Eine Definition innerhalb eines {@link Word}.
 * Hält den Fremdschlüssel zum zugehörigen Wort.
 */
@Entity
@Table(name = "definitions")
public class Definition {

    /** Eindeutige Id für die Definition. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Die eigentliche Definition des Wortes, meist in Satzform. */
    private String definition;

    /**Die Wortart der Definition, z.B. noun, verb oder adjective. */
    private String partOfSpeech;

    /** Ob die Definition favorisiert ist oder nicht. */
    private boolean isFavoriteDefinition;

    /** Notiz, welche einem favorisierten Wort zugefügt werden kann. */
    private String note;

    /** Liste von möglichen Synonymen der Definition. */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> synonyms;

    /** Liste von möglichen Beispielsätzen für die Definition. */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> examples;

    /** Liste von möglichen Antonymen der Definition. */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> antonyms;

    /** Fremdschlüssel zu Wort */
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    /** Parameterloser Konstruktor, den JPA/Hibernate braucht */
    public Definition(){

    }

    /**
     * Erstellt eine neue Definition mit allen Feldern.
     * Der Backlink zu {@link Word} wird nicht hier gesetzt, sondern via {@link Word#addDefinition(Definition)}.
     *
     * @param definition          Die eigentliche Definition als Text.
     * @param partOfSpeech        Die Wortart, z.B. {@code noun} oder {@code verb}.
     * @param isFavoriteDefinition Ob die Definition favorisiert ist.
     * @param note                Optionale persönliche Notiz.
     * @param examples            Liste von Beispielsätzen.
     * @param synonyms            Liste von Synonymen.
     * @param antonyms            Liste von Antonymen.
     */
    public Definition(String definition, String partOfSpeech, boolean isFavoriteDefinition, String note, List<String> examples, List<String> synonyms, List<String> antonyms) {
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.isFavoriteDefinition = isFavoriteDefinition;
        this.note = note;
        this.examples = examples;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public boolean isFavoriteDefinition() {
        return isFavoriteDefinition;
    }

    public void setFavoriteDefinition(boolean favoriteDefinition) {
        isFavoriteDefinition = favoriteDefinition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}

