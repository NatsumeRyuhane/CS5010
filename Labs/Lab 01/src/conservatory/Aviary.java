package conservatory;
import birds.Bird;
import utils.BirdFood;

import java.util.*;

/**
 * The Aviary class is an abstract class that represents an aviary in the conservatory.
 * Aviary is responsible for managing the birds in the aviary, accepting new birds, and calculating the food requirement for the birds in the aviary.
 */
public abstract class Aviary {
    // the maximum capacity of an aviary
    public final static int AVIARY_MAX_CAPACITY = 5;
    // the ID of the aviary, id should be unique in a conservatory
    private final int id;
    // the birds in the aviary
    private final Bird[] birds = new Bird[Aviary.AVIARY_MAX_CAPACITY];

    public Aviary(int id) {
        this.id = id;
    }

    /**
     * Get the ID of the aviary
     * @return the ID of the aviary
     */

    public int getID() {
        return id;
    }

    /**
     * Get the string represents the type of the aviary
     * @return the type of the aviary
     */
    abstract public String getAviaryType();

    /**
     * Get the birds in the aviary
     * @return the birds in the aviary
     */
    public Bird[] getBirds() {
        return birds;
    }

    /**
     * Get the occupancy of the aviary
     * @return the number of birds in the aviary
     */
    public int getOccupancy() {
        int count = 0;
        for (Bird bird : birds) {
            if (bird != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Check if a bird can be accepted to the aviary
     * @param bird: the bird to accept
     * @return true if the bird is accepted, false otherwise
     */
    public boolean acceptBird(Bird bird) {
        // to accept a new bird:
        // 1. check if the bird is marked as instinct
        if (bird.isInstinct()) {
            return false;
        }

        // 2. check if the bird is already in the aviary, or the bird has the same name as the bird in the aviary
        //    (birds are not allowed to have the same name in the same aviary, as it breaks isBirdInAviary method)
        if (this.isBirdInAviary(bird)) {
            return false;
        }

        // 3. check if the aviary is full
        boolean isFull = true;
        for (Bird value : birds) {
            if (value == null) {
                isFull = false;
                break;
            }
        }
        if (isFull) {
            return false;
        }


        // 4. check if the bird is compatible with the other birds in the aviary
        //    (implemented in the subclass)
        return true;
    }

    /**
     * Add a bird to the aviary
     * @param bird: the bird to add
     * @throws IllegalArgumentException if the bird cannot be added to the aviary
     */
    public void addBird(Bird bird) {
        if (!acceptBird(bird)) {
            throw new IllegalArgumentException("Bird cannot be added to the aviary");
        }

        for (int i = 0; i < birds.length; i++) {
            if (birds[i] == null) {
                birds[i] = bird;
                break;
            }
        }
    }

    /**
     * Get a bird from the aviary by name
     * @param birdName the name of the bird to get
     * @return the bird with the specified name
     * @throws IllegalArgumentException if the bird is not in the aviary
     */
    public Bird getBirdByName(String birdName) {
        for (Bird bird : birds) {
            if (bird != null && bird.getName().equals(birdName)) {
                return bird;
            }
        }

        throw new IllegalArgumentException("Specified bird is not in the aviary");
    }

    /**
     * remove a bird from the aviary by name
     * NOTE: while this method works, it is not required in the requirements
     * this method is implemented for future extension
     * @param birdName the name of the bird to remove
     * @throws IllegalArgumentException if the bird is not in the aviary
     */
    public void removeBirdByName(String birdName) {
        Bird targetBird = getBirdByName(birdName);

        if (!isBirdInAviary(targetBird)) {
            throw new IllegalArgumentException("Specified bird is not in the aviary");
        }

        for (int i = 0; i < birds.length; i++) {
            if (birds[i] != null && birds[i].equals(targetBird)) {
                birds[i] = null;
                break;
            }
        }
    }

    /**
     * calculate the total food requirement for the birds in the aviary
     * birds are assumed to be fed using 1 unit of bird.preferredFood[0]
     * @return a dictionary of the food requirement
     */
    public Map<BirdFood, Integer> calcFoodRequirement() {
        Map<BirdFood, Integer> foodRequirement = new HashMap<>();
        // iterate through the birds in the aviary
        for (Bird bird : birds) {
            if (bird != null) {
                // get the preferred food list for the bird
                BirdFood[] foodList = bird.getPreferredFood();

                BirdFood food = null;
                // assume the bird is fed using the first food in the list
                for (BirdFood birdFood : foodList) {
                    if (birdFood != null) {
                        food = birdFood;
                        break;
                    }
                }
                // error handling if foodList is empty
                if (food == null) {
                    throw new IllegalStateException("The preferred food for bird" + bird.getName() + " is not set properly.");
                }

                // process the food requirement to the dictionary
                if (foodRequirement.get(food) == null) {
                    foodRequirement.put(food, 1);
                } else {
                    foodRequirement.put(food, foodRequirement.get(food) + 1);
                }
            }
        }
        return foodRequirement;
    }

    /**
     * Check if a bird is in the aviary by name
     * @param bird the bird to check
     * @return true if the bird is in the aviary, false otherwise
     */
    public boolean isBirdInAviary(Bird bird) {
        for (Bird otherBird : this.birds) {
            if (bird != null && bird.equals(otherBird)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the string representation of the aviary
     * note that it will also call the toString method of the birds in the aviary
     * @return the string representation of the aviary
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aviary{\n")
                .append("  id=").append(getID())
                .append(", type=").append(getAviaryType())
                .append(",\n  birds{\n");

        for (int i = 0; i < birds.length; i++) {
            if (birds[i] != null) {
                sb.append("    ")
                  .append(birds[i].toString());
                if (i < birds.length - 1) {
                    sb.append("},\n");
                }
            }
        }
        sb.append("  }");
        return sb.toString();
    }
}
