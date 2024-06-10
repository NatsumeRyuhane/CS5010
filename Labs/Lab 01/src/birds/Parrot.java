package birds;

import utils.BirdFood;

public class Parrot extends Bird{
    // maximum allowed vocabulary size for all parrots
    private static final int MAX_ALLOWED_VOCABULARY_SIZE = 100;
    private final String[] vocabulary = new String[Parrot.MAX_ALLOWED_VOCABULARY_SIZE];
    // index of the favorite saying in the vocabulary array (as the favorite saying is part of the vocabulary array)
    // if the value is negative, it means the favorite saying is not set
    private int favoriteSayingIndex = 0;

    /**
     * Constructor for Parrot
     * @param name name of the parrot
     * @param speciesName species name of the parrot
     * @param characteristics characteristics of the parrot
     * @param numberOfWings number of wings of the parrot
     * @param preferredFood preferred food of the parrot
     * @param isInstinct instinct of the parrot
     * @param vocabulary vocabulary of the parrot
     * @param favoriteSayingIndex favorite saying index of the parrot
     */
    public Parrot(String name, String speciesName, String characteristics, int numberOfWings,
                  BirdFood[] preferredFood, boolean isInstinct, String[] vocabulary, int favoriteSayingIndex) {
        super(name, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
        this.setVocabulary(vocabulary);
        this.setFavoriteSayingIndex(favoriteSayingIndex);
    }

    /**
     * Get the vocabulary of the parrot
     * @return vocabulary of the parrot
     */
    public String[] getVocabulary() {
        return vocabulary;
    }

    /**
     * Get the vocabulary size of the parrot (the non-null elements in the vocabulary array)
     * @return vocabulary size of the parrot
     */
    public int getVocabularySize() {
        int count = 0;
        for (String word : vocabulary) {
            if (word != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Set the vocabulary of the parrot
     * @param vocabulary vocabulary of the parrot
     */
    public void setVocabulary(String[] vocabulary) {
        if (vocabulary.length > Parrot.MAX_ALLOWED_VOCABULARY_SIZE) {
            throw new IllegalArgumentException("Vocabulary size too large");
        }

        System.arraycopy(
            vocabulary, 0,
            this.vocabulary, 0,
            vocabulary.length
        );
    }

    /**
     * Get the index of the favorite saying of the parrot
     * @return favorite saying index of the parrot
     */
    public int getFavoriteSayingIndex() {
        return favoriteSayingIndex;
    }

    /**
     * Get the favorite saying of the parrot as a string
     * @return favorite saying of the parrot
     * @throws IllegalStateException if the favorite saying is not set
     * @throws IllegalStateException if the favorite saying index is invalid
     * @throws IllegalStateException if the favorite saying index points to a null value in the vocabulary array
     */
    public String getFavoriteSayingAsString() {
        if (favoriteSayingIndex < 0) {
            throw new IllegalStateException("Favourite saying unset");
        }
        if (favoriteSayingIndex >= Parrot.MAX_ALLOWED_VOCABULARY_SIZE) {
            throw new IllegalStateException("Invalid index value");
        }
        if (vocabulary[favoriteSayingIndex] == null) {
            throw new IllegalStateException("Favourite saying index points to a null value in the vocabulary array");
        }
        return vocabulary[favoriteSayingIndex];
    }

    /**
     * Set the favorite saying index of the parrot
     * @param favoriteSayingIndex favorite saying index of the parrot
     * @throws IllegalArgumentException if the favorite saying index is invalid
     * @throws IllegalStateException if the favorite saying is not set
     * @throws IllegalStateException if the favorite saying index points to a null value in the vocabulary array
     */
    public void setFavoriteSayingIndex(int favoriteSayingIndex) {
        if (favoriteSayingIndex >= Parrot.MAX_ALLOWED_VOCABULARY_SIZE) {
            throw new IllegalArgumentException("Invalid index value");
        } 
        if (favoriteSayingIndex < 0) {
            throw new IllegalStateException("Favourite saying unset");
        }
        if (vocabulary[favoriteSayingIndex] == null) {
            throw new IllegalStateException("Favourite saying index points to a null value in the vocabulary array");
        }
        this.favoriteSayingIndex = favoriteSayingIndex;
    }

    /**
     * Add a word to the vocabulary of the parrot
     * the word is added to the first available slot in the vocabulary array
     * @param word word to be added to the vocabulary
     * @throws IllegalStateException if the vocabulary is full
     */
    public void addWord(String word) {
        for (int i = 0; i < Parrot.MAX_ALLOWED_VOCABULARY_SIZE; i++) {
            if (this.vocabulary[i] == null) {
                this.vocabulary[i] = word;
                return;
            }
        }
        throw new IllegalStateException("Vocabulary is full");
    }

    /**
     * Remove a word from the vocabulary of the parrot
     * @param word word to be removed from the vocabulary
     * @throws IllegalStateException if the word is not found
     */
    public void removeWord(String word) {
        for (int i = 0; i < Parrot.MAX_ALLOWED_VOCABULARY_SIZE; i++) {
            if (this.vocabulary[i] != null && this.vocabulary[i].equals(word)) {
                this.removeWord(i);
                return;
            }
        }
        throw new IllegalStateException("Word not found");
    }

    /**
     * Remove a word from the vocabulary of the parrot
     * @param index index of the word to be removed from the vocabulary
     * @throws IllegalArgumentException if the index is invalid
     * @throws IllegalStateException if the favorite saying is set to the word to be removed
     */
    public void removeWord(int index) {
        if (index < 0 || index >= Parrot.MAX_ALLOWED_VOCABULARY_SIZE) {
            throw new IllegalArgumentException("Invalid index value");
        }

        if (index == this.getFavoriteSayingIndex()) {
            throw new IllegalStateException("Cannot remove favourite saying; Set a new favourite saying first");
        }
        this.vocabulary[index] = null;
    }

    /**
     * Get the string representation of the parrot
     * @return string representation of the parrot
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());

        //filter out null values in vocabulary
        String[] deNullifiedVocabulary = new String[this.getVocabularySize()];
        int j = 0;
        for (int i = 0; i < Parrot.MAX_ALLOWED_VOCABULARY_SIZE; i++) {
            if (this.vocabulary[i] != null) {
                deNullifiedVocabulary[j] = this.vocabulary[i];
                j++;
            }
        }

        // remove the last { at the end of the string
        sb.deleteCharAt(sb.length() - 1);
        sb.append(", vocabulary=")
          .append(java.util.Arrays.toString(deNullifiedVocabulary))
          .append(", favoriteSaying=")
          .append("\"").append(this.getFavoriteSayingAsString()).append("\"")
          .append("}");

        return sb.toString();
    }
}
