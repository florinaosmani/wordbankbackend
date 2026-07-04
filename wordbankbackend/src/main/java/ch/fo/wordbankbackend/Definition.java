package ch.fo.wordbankbackend;

import java.util.List;

public class Definition {
    private String id;
    private String definition;
    private String partOfSpeech;
    private boolean isFavoriteDefinition;
    private String note;
    private List<String> synonyms;
    private List<String> examples;
    private List<String> antonyms;

    public Definition(String id, String definition, String partOfSpeech, boolean isFavoriteDefinition, String note, List<String> examples, List<String> synonyms, List<String> antonyms) {
        this.id = id;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.isFavoriteDefinition = isFavoriteDefinition;
        this.note = note;
        this.examples = examples;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
