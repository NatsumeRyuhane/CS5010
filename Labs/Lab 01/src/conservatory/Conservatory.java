package conservatory;
import birds.*;
import utils.*;

import java.util.*;

/**
 * The conservatory class is responsible for managing the aviaries and the birds in the conservatory.
 * Conservatory can rescue and release birds, calculate the food requirement for the conservatory,
 * and provide a map of the conservatory and a bird index
 */
public class Conservatory {
    // the next aviary ID to be assigned
    private int nextAviaryID = 0;
    // the maximum limit of number of aviaries in the conservatory
    public static final int MAX_AVIARIES = 20;
    // the aviaries in the conservatory
    private final Aviary[] aviaries = new Aviary[Conservatory.MAX_AVIARIES];
    // a dictionary to keep track of which bird is in which aviary
    // NOTE: this can also be done by iterating through the aviaries and checking each bird
    //       but this dictionary is more efficient
    private final Map<Bird, Integer> birdIndex = new HashMap<>();

    /**
     * Add an aviary to the conservatory
     * @param aviary: the aviary to add
     * @throws IllegalArgumentException if aviary is null
     * @throws IllegalStateException if there is no more space for new aviaries
     */
    private void addAviary(Aviary aviary) {
        if (aviary == null) {
            throw new IllegalArgumentException("Aviary cannot be null");
        }

        for (int i = 0; i < aviaries.length; i++) {
            if (aviaries[i] == null) {
                aviaries[i] = aviary;
                nextAviaryID++;
                return;
            }
        }

        throw new IllegalStateException("No more space for new aviaries");
    }

    /**
     * Get all aviaries in the conservatory
     * @return the aviaries in the conservatory
     */
    public Aviary[] getAviaries() {
        return aviaries;
    }

    /**
     * Get an aviary by ID
     * @param id: the ID of the aviary
     * @return the aviary with the specified ID
     * @throws IllegalArgumentException if the aviary is not found
     */
    public Aviary getAviaryByID(int id) {
        for (Aviary aviary : aviaries) {
            if (aviary.getID() == id) {
                return aviary;
            }
        }

        throw new IllegalArgumentException("Aviary not found");
    }

    /**
     * rescue and assign a bird to a suitable aviary
     * @param bird: the bird to assign
     * @throws IllegalArgumentException if the bird is already in the conservatory
     */
    public void rescueBird(Bird bird) {
        // check if the bird is already in the conservatory
        if (birdIndex.containsKey(bird)) {
            throw new IllegalArgumentException("Bird is already in the conservatory");
        }

        // loop through the aviaries to find a suitable aviary
        for (Aviary aviary : this.getAviaries()) {
            if (aviary == null) {
                continue;
            }

            if (aviary.acceptBird(bird)) {
                aviary.addBird(bird);
                birdIndex.put(bird, aviary.getID());
                return;
            }
        }

        // if no aviary is suitable, try to create a new aviary for this bird
       Aviary newAviary = this.createAviaryForBird(bird);
       try {
           newAviary.addBird(bird);
           this.addAviary(newAviary);;
           birdIndex.put(bird, newAviary.getID());
       } catch (IllegalArgumentException e) {
           throw new IllegalStateException("No suitable aviary and no space for a new aviary");
       }
    }

    /**
     * release a bird from the conservatory
     * NOTE: while this method works, it is not required in the requirements
     * this method is implemented for future extension
     * @param bird: the bird to release
     * @throws IllegalArgumentException if the bird is not in the conservatory
     */
    public void releaseBird(Bird bird) {
        if (!birdIndex.containsKey(bird)) {
            throw new IllegalArgumentException("Bird is not in the conservatory");
        }

        Aviary aviary = getAviaryByID(birdIndex.get(bird));
        aviary.removeBirdByName(bird.getName());
        birdIndex.remove(bird);
    }

    /**
     * Calculate the food requirement for the conservatory
     * @return the food requirement for the conservatory as a string
     */
    public String calcFoodRequirement() {
        Map<BirdFood, Integer> foodRequirement = new HashMap<>();
        for (Aviary aviary : aviaries) {
            if (aviary == null) {
                continue;
            }

            Map<BirdFood, Integer> aviaryFoodRequirement = aviary.calcFoodRequirement();

            for (BirdFood food : aviaryFoodRequirement.keySet()) {
                if (foodRequirement.get(food) == null) {
                    foodRequirement.put(food, aviaryFoodRequirement.get(food));
                } else {
                    foodRequirement.put(food, foodRequirement.get(food) + aviaryFoodRequirement.get(food));
                }
            }
        }

        // convert the dictionary to a string
        StringBuilder sb = new StringBuilder();
        sb.append("Food requirement for the conservatory:\n");
        for (BirdFood food : foodRequirement.keySet()) {
            sb.append("  ");
            sb.append(food.toString());
            sb.append(": ");
            sb.append(foodRequirement.get(food));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Creates an aviary for a bird
     * Note that this method only creates a suitable aviary,
     * it does not add the bird to the aviary or add the aviary to the conservatory
     * @param bird: the bird to create an aviary for
     * @return the created aviary
     * @throws IllegalArgumentException if bird is null
     */
    private Aviary createAviaryForBird(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Aviary cannot be null");
        }

        Aviary aviary;
        if (bird instanceof birds.BirdOfPrey) {
            aviary = new BirdsOfPreyAviary(nextAviaryID);
        }
        else if (bird instanceof birds.FlightlessBird){
            aviary = new FlightlessBirdAviary(nextAviaryID);
        }
        else if (bird instanceof birds.Waterfowl) {
            aviary = new WaterfowlAviary(nextAviaryID);
        }
        else {
            aviary = new GenericAviary(nextAviaryID);
        }

        return aviary;
    }

    /**
     * Get the map of the conservatory
     * the map includes the aviaries and the birds in each aviary
     * @return the map of the conservatory as a string
     */
    public String conservatoryMap() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conservatory Map:\n");
        for (Aviary aviary : aviaries) {
            if (aviary != null) {
                sb.append("  [").append(aviary.getID()).append("] ").append(aviary.getAviaryType())
                        .append(" (").append(aviary.getOccupancy()).append("/").append(Aviary.AVIARY_MAX_CAPACITY).append(")");

                for (Bird bird : aviary.getBirds()) {
                    if (bird != null) {
                        sb.append("\n    ").append(bird.getName()).append(": ").append(bird.getSpeciesName());
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Get the index of all birds in the conservatory
     * @return the bird index as a string
     */
    public String getBirdIndexAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bird Index:\n");
        List<Bird> birds = new ArrayList<Bird>(birdIndex.keySet());
        birds.sort(Comparator.comparing(Bird::getName));
        for (Bird bird : birds) {
            sb.append("  ").append(bird.getName()).append(" -> [").append(birdIndex.get(bird)).append("]\n");
        }
        return sb.toString();
    }

    /**
     * Get a string representation of the conservatory
     * the string representation includes the aviaries and the birds in each aviary
     * it will recursively call the toString method of each aviary and each bird
     * @return a string representation of the conservatory
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conservatory {\n");
        for (Aviary aviary : aviaries) {
            if (aviary != null) {
                sb.append("  ").append(aviary.toString().replace("\n", "\n  ").replace("  }", "}")).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}