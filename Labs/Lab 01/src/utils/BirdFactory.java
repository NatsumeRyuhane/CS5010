package utils;
import birds.*;

/**
 * This class is used to create different types of birds.
 */
public class BirdFactory {
    /**
     * This method creates a bird based on the characteristics of the bird. (that is not a parrot or a bird that lives by water
     * @param birdName the name of the bird
     * @param speciesName the species of the bird
     * @param characteristics the characteristics of the bird
     * @param numberOfWings the number of wings the bird has
     * @param preferredFood the preferred food of the bird
     * @param isInstinct whether the bird is acting on instinct
     * @return the bird
     * @throws IllegalArgumentException if the bird characteristics are not registered (in utils.BirdCharacteristics)
     */
    public static Bird createBird(String birdName, String speciesName, String characteristics, int numberOfWings,
                      BirdFood[] preferredFood, boolean isInstinct) {
        switch (characteristics) {
            case BirdCharacteristics.BIRDS_OF_PREY:
                return new birds.BirdOfPrey(birdName, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
            case BirdCharacteristics.FLIGHTLESS_BIRDS:
                return new birds.FlightlessBird(birdName, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
            case BirdCharacteristics.OWLS:
                return new birds.Owl(birdName, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
            case BirdCharacteristics.PIGEONS:
                return new birds.Pigeon(birdName, speciesName, characteristics, numberOfWings, preferredFood, isInstinct);
            default:
                throw new IllegalArgumentException("Unregistered bird characteristics; Cannot determine bird type.");
        }
    }

    /**
     * This method creates a bird that lives by water based on the characteristics of the bird. (waterfowl or shorebird)
     * @param birdName the name of the bird
     * @param speciesName the species of the bird
     * @param characteristics the characteristics of the bird
     * @param numberOfWings the number of wings the bird has
     * @param preferredFood the preferred food of the bird
     * @param isInstinct whether the bird is acting on instinct
     * @param bodyOfWaterLivesBy the body of water the bird lives by
     * @return the bird
     * @throws IllegalArgumentException if the bird characteristics are not registered (in utils.BirdCharacteristics)
     */
    public static BirdsLiveByWater createBird(String birdName, String speciesName, String characteristics, int numberOfWings,
                                       BirdFood[] preferredFood, boolean isInstinct, String bodyOfWaterLivesBy) {
        switch (characteristics) {
            case BirdCharacteristics.SHOREBIRDS:
                return new birds.Shorebird(birdName, speciesName, characteristics, numberOfWings, preferredFood, isInstinct, bodyOfWaterLivesBy);
            case BirdCharacteristics.WATERFOWL:
                return new birds.Waterfowl(birdName, speciesName, characteristics, numberOfWings, preferredFood, isInstinct, bodyOfWaterLivesBy);
            default:
                throw new IllegalArgumentException("Unregistered bird characteristics; Cannot determine bird type.");
        }

    }

    /**
     * This method creates a parrot based on the characteristics of the bird.
     * @param birdName the name of the bird
     * @param speciesName the species of the bird
     * @param characteristics the characteristics of the bird
     * @param numberOfWings the number of wings the bird has
     * @param preferredFood the preferred food of the bird
     * @param isInstinct whether the bird is acting on instinct
     * @param vocabulary the vocabulary of the bird
     * @param favouriteSayingIndex the index of the bird's favourite saying
     * @return the bird
     */
    public static Parrot createBird(String birdName, String speciesName, String characteristics, int numberOfWings,
                             BirdFood[] preferredFood, boolean isInstinct, String[] vocabulary, int favouriteSayingIndex) {
            return new Parrot(birdName, speciesName, characteristics, numberOfWings, preferredFood,
                    isInstinct, vocabulary, favouriteSayingIndex);
    }
}
