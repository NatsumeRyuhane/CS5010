package rpg;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A factory class for fast creation of equipment with random adjectives.
 * This factory class will create equipment with random adjectives based on a template of equipment.
 */
public class EquipmentFactory {
    // default maximum number of adjectives to add to equipment
    public static final int DEFAULT_ADJECTIVE_MAX_NUM = 2;
    // a map of equipment templates
    private static final Map<String, Equipment> templateEquipments = new HashMap<>(
        Map.ofEntries(
            Map.entry("Hat", new HeadEquipment("Hat", 1)),
            Map.entry("Helmet", new HeadEquipment("Helmet", 5)),
            Map.entry("Visor", new HeadEquipment("Visor", 2)),
            Map.entry("Glove", new HandEquipment("Glove", 5)),
            Map.entry("Sword", new HandEquipment("Sword", 10)),
            Map.entry("Shield", new HandEquipment("Shield", 8)),
            Map.entry("Boots", new FootEquipment("Boots", 3, 5)),
            Map.entry("Sneakers", new FootEquipment("Sneakers", 5, 8)),
            Map.entry("HoverBoard", new FootEquipment("Sandals", 10, 15))
        )
    );

    /**
     * Enchants equipment with random adjectives up to a maximum number
     * Should be named to something like "addRandomAdjectives",
     * but enchant sounds cooler
     * @param e Equipment to enchant
     * @param numAdjectives Maximum number of adjectives to add, should be non-negative and less than or equal to all adjectives, else will be set to number of all adjectives available
     * @throws IllegalArgumentException if numAdjectives is negative
     */
    public static void enchant(Equipment e, int numAdjectives) {
        if (numAdjectives < 0) {
            throw new IllegalArgumentException("Number of adjectives must be non-negative");
        }

        numAdjectives = Math.min(numAdjectives, Equipment.getAllAdjectivesList().size());

        while (e.getAdjectives().size() < numAdjectives) {
            String[] adjectives = Equipment.getAllAdjectivesList().toArray(new String[0]);
            String randomAdjective = adjectives[new Random().nextInt(adjectives.length)];
            e.addAdjective(randomAdjective);
        }
    }

    /**
     * Create a random equipment with random adjectives
     * @return Random equipment with random adjectives
     */
    public static Equipment createRandomEquipment() {
        Random rand = new Random();
        String[] keys = templateEquipments.keySet().toArray(new String[0]);
        String randomKey = keys[rand.nextInt(keys.length)];
        Equipment e = templateEquipments.get(randomKey).clone();

        int numAdjectives = rand.nextInt(DEFAULT_ADJECTIVE_MAX_NUM + 1);
        EquipmentFactory.enchant(e, numAdjectives);

        return e;
    }

    /**
     * Create an array of random equipment with random adjectives
     * @param num Number of equipment to create
     * @return Array of random equipment with random adjectives
     */
    public static Equipment[] createRandomEquipment(int num) {
        Equipment[] equipments = new Equipment[num];
        for (int i = 0; i < num; i++) {
            equipments[i] = createRandomEquipment();
        }
        return equipments;
    }

    /**
     * Create a random equipment with random adjectives
     * @param name Name of equipment, should be defined in templateEquipments
     * @return equipment with random adjectives
     */
    public static Equipment createRandomizedEquipment(String name) {
        // check if name is defined in keys of templateEquipments
        if (!templateEquipments.containsKey(name)) {
            throw new IllegalArgumentException("Equipment name not found in templateEquipments");
        }

        Random rand = new Random();
        Equipment e = templateEquipments.get(name).clone();

        int numAdjectives = rand.nextInt(DEFAULT_ADJECTIVE_MAX_NUM + 1);
        EquipmentFactory.enchant(e, numAdjectives);

        return e;
    }

    /**
     * Create a random equipment with random adjectives
     * @param name Name of equipment, should be defined in templateEquipments
     * @param numAdjectives Number of adjectives to add to equipment, should be non-negative
     * @return equipment with random adjectives
     */
    public static Equipment createRandomizedEquipment(String name, int numAdjectives) {
        // check if name is defined in keys of templateEquipments
        if (!templateEquipments.containsKey(name)) {
            throw new IllegalArgumentException("Equipment name not found in templateEquipments");
        }

        if (numAdjectives < 0) {
            throw new IllegalArgumentException("Number of adjectives must be non-negative");
        }

        Equipment e = templateEquipments.get(name).clone();
        EquipmentFactory.enchant(e, numAdjectives);

        return e;
    }

    /**
     * Create equipment with adjectives
     * @param name Name of equipment, should be defined in templateEquipments
     * @param adjectives Adjectives to add to equipment, should be defined in Equipment.adjectiveList, else will be ignored
     * @return Equipment with adjectives
     */
    public static Equipment createEquipmentWithAdjectives(String name, String[] adjectives) {
        // check if name is defined in keys of templateEquipments
        if (!templateEquipments.containsKey(name)) {
            throw new IllegalArgumentException("Equipment name not found in templateEquipments");
        }

        Equipment e = templateEquipments.get(name).clone();
        for (String adjective : adjectives) {
            try {
                e.addAdjective(adjective);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return e;
    }
}
