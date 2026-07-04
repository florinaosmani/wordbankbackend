package ch.fo.wordbankbackend.model;

import java.util.List;

public class Word {
    private String id;
    private String word;
    private boolean isFavoriteWord;
    private int syllables;
    private String pronunciation;
    private List<Definition> results;

    public Word(String id, String word, boolean isFavoriteWord, int syllables, String pronunciation, List<Definition> results) {
        this.id = id;
        this.word = word;
        this.isFavoriteWord = isFavoriteWord;
        this.syllables = syllables;
        this.pronunciation = pronunciation;
        this.results = results;
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
