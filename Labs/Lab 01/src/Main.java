import conservatory.*;
import utils.*;
public class Main {
    public static void main(String[] args) {

        System.out.println("Note: This jar is not interactive. It is a demonstration of the functionality of the Conservatory class.");

        Conservatory conservatory = new Conservatory();

        // Add some birds to the conservatory
        conservatory.rescueBird(BirdFactory.createBird(
                "Kaka", "Parrot",
                BirdCharacteristics.PARROTS,
                2,
                new BirdFood[]{BirdFood.FRUIT, BirdFood.INSECTS}, false,
                new String[]{"Hello", "Goodbye", "Thank you"},
                2));
        conservatory.rescueBird(BirdFactory.createBird(
                "AAAA", "Eagle",
                BirdCharacteristics.BIRDS_OF_PREY,
                2,
                new BirdFood[]{BirdFood.FISH, BirdFood.LARVAE, BirdFood.NUTS}, false));
        conservatory.rescueBird(BirdFactory.createBird(
                "BBBB", "Kiwi",
                BirdCharacteristics.BIRDS_OF_PREY,
                2,
                new BirdFood[]{BirdFood.OTHER_BIRDS, BirdFood.NUTS}, false));
        conservatory.rescueBird(BirdFactory.createBird(
                "CCCC", "someStupidSpeciesName",
                BirdCharacteristics.WATERFOWL,
                2,
                new BirdFood[]{BirdFood.FISH, BirdFood.FRUIT, BirdFood.NUTS, BirdFood.EGGS}, false,
                "Lake"));

        // print some information about the conservatory
        System.out.print(conservatory.conservatoryMap());
        System.out.println();
        System.out.print(conservatory.getBirdIndexAsString());
        System.out.println();
        System.out.print(conservatory.calcFoodRequirement());
        System.out.println();
        System.out.print(conservatory.toString());
        System.out.println();
    }
}