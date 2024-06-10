package birds;

import utils.BirdFood;

import java.util.Arrays;
import java.util.Objects;

public abstract class Bird implements Comparable<Bird>{
    private String name;
    private String speciesName;
    private String characteristics;
    private int numberOfWings;
    private final BirdFood[] preferredFood = new BirdFood[4];
    private boolean instinct;

    /**
     * Create a Bird object with the given parameters
     * @param name the name of the bird
     * @param speciesName the species name of the bird
     * @param characteristics the characteristics of the bird
     * @param numberOfWings the number of wings of the bird
     * @param preferredFood the preferred food of the bird
     * @param instinct whether the bird is instinct
     */
    public Bird(String name, String speciesName, String characteristics, int numberOfWings, BirdFood[] preferredFood, boolean instinct) {
        this.setName(name);
        this.setSpeciesName(speciesName);
        this.setCharacteristics(characteristics);
        this.setNumberOfWings(numberOfWings);
        this.setPreferredFood(preferredFood);
        this.setIsInstinct(instinct);
    }

    /**
     * Set the name of the bird
     * @param name the name of the bird
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the bird
     * @return the name of the bird
     */
    public String getName() {
        return name;
    }

    /**
     * Set the species name of the bird
     * @param speciesName the species name of the bird
     */
    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    /**
     * Get the species name of the bird
     * @return the species name of the bird
     */
    public String getSpeciesName() {
        return speciesName;
    }

    /**
     * Set the characteristics of the bird
     * @param characteristics the characteristics of the bird
     */
    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    /**
     * Get the characteristics of the bird
     * @return the characteristics of the bird
     */
    public String getCharacteristics() {
        return characteristics;
    }

    /**
     * Set the number of wings of the bird
     * @param numberOfWings the number of wings of the bird
     */
    public void setNumberOfWings(int numberOfWings) {
        this.numberOfWings = numberOfWings;
    }

    /**
     * Get the number of wings of the bird
     * @return the number of wings of the bird
     */
    public int getNumberOfWings() {
        return numberOfWings;
    }

    /**
     * Set whether the bird is instinct
     * @param isInstinct whether the bird is instinct
     */
    public void setIsInstinct(boolean isInstinct) {
        this.instinct = isInstinct;
    }

    /**
     * Get whether the bird is instinct
     * @return whether the bird is instinct
     */
    public boolean isInstinct() {
        return instinct;
    }

    /**
     * update the preferredFood array with the given array
     * @param preferredFood the array of preferredFood to set
     * @throws IllegalArgumentException if the length of the preferredFood array is not in [2, 4]
     * @throws IllegalArgumentException if the preferredFood array contains duplicate elements
     */
    public void setPreferredFood(BirdFood[] preferredFood) {
        if (preferredFood.length < 2 || preferredFood.length > 4) {
            throw new IllegalArgumentException("The length of the preferredFood array should be in [2, 4]");
        }

        // check for element duplication
        for (int i = 0; i < preferredFood.length; i++) {
            for (int j = i + 1; j < preferredFood.length; j++) {
                if (preferredFood[i] != null && preferredFood[i].equals(preferredFood[j])) {
                    throw new IllegalArgumentException("The preferredFood array should not contain duplicate elements");
                }
            }
        }

        System.arraycopy(
            preferredFood, 0,
            this.preferredFood, 0,
            preferredFood.length
        );
    }

    /**
     * Get the preferred food of the bird
     * @return the preferred food of the bird
     */
    public BirdFood[] getPreferredFood() {
        return preferredFood;
    }

    /**
     * Get the preferred food of the bird in a string format
     * @return the preferred food of the bird in a string format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // filter out null elements in the preferredFood array
        BirdFood[] filteredPreferredFood = Arrays.stream(preferredFood)
            .filter(Objects::nonNull)
            .toArray(BirdFood[]::new);

        sb.append("Bird{")
          .append("name='").append(name).append('\'')
          .append(", speciesName='").append(speciesName).append('\'')
          .append(", characteristics='").append(characteristics).append('\'')
          .append(", numberOfWings=").append(numberOfWings)
          .append(", preferredFood=").append(Arrays.toString(filteredPreferredFood))
          .append(", isInstinct=").append(instinct)
          .append('}');

        return sb.toString();
    }

    /**
     * Compare this bird with the given bird based on their names' alphabetical order
     * @param bird the bird to compare with
     * @return a negative integer, zero, or a positive integer as this bird is less than, equal to, or greater than the given bird
     */
    @Override
    public int compareTo(Bird bird) {
        return this.name.compareTo(bird.name);
    }
}
