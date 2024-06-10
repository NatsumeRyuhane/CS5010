package rpg;

import java.util.*;

abstract public class Equipment implements Comparable<Equipment> {
    // Map of all adjectives that can be applied to equipments
    private static final Map<String, AdjectiveModifier> allAdjectives = new HashMap<>(
        Map.ofEntries(
            Map.entry("Shiny", new AdjectiveModifier(1, 1)),
            Map.entry("Dull", new AdjectiveModifier(-1, -1)),
            Map.entry("Rusty", new AdjectiveModifier(-2, -2)),
            Map.entry("Polished", new AdjectiveModifier(2, 2)),
            Map.entry("Sharp", new AdjectiveModifier(2, 0)),
            Map.entry("Heavy", new AdjectiveModifier(0, 2)),
            Map.entry("Light", new AdjectiveModifier(0, -2)),
            Map.entry("Sturdy", new AdjectiveModifier(1, 1)),
            Map.entry("Fragile", new AdjectiveModifier(-1, -1)),
            Map.entry("Enchanted", new AdjectiveModifier(3, 3)),
            Map.entry("Cursed", new AdjectiveModifier(-5, -5)),
            Map.entry("Blessed", new AdjectiveModifier(3, 3)),
            Map.entry("Damned", new AdjectiveModifier(-3, -3)),
            Map.entry("Legendary", new AdjectiveModifier(5, 5)),
            Map.entry("Common", new AdjectiveModifier(0, 0)),
            Map.entry("Uncommon", new AdjectiveModifier(1, 1)),
            Map.entry("Rare", new AdjectiveModifier(2, 2))
        )
    );

    /**
     * Register an adjective to the list of all adjectives
     * @param adjective the adjective to add
     * @param modifier the modifier to apply when the adjective is used
     * @throws IllegalArgumentException if the adjective is null or empty, or if the adjective already exists
     */
    public static void addNewAdjective(String adjective, AdjectiveModifier modifier) {
        if (adjective == null || adjective.isEmpty()) {
            throw new IllegalArgumentException("Adjective cannot be null or empty");
        }

        if (allAdjectives.containsKey(adjective)) {
            throw new IllegalArgumentException("Adjective already exists: " + adjective);
        }

        if (modifier == null) {
            throw new IllegalArgumentException("Modifier cannot be null");
        }

        allAdjectives.put(adjective, modifier);
    }

    /**
     * Get the adjective modifier for a given adjective.
     * @param adjective the adjective to get the modifier for
     * @return AdjectiveModifier the modifier for the given adjective
     * @throws IllegalArgumentException if the adjective is not recognized
     */
    public static AdjectiveModifier getAdjectiveModifier(String adjective) {
        AdjectiveModifier mod = allAdjectives.get(adjective);
        if (mod == null) {
            throw new IllegalArgumentException("Unrecognized adjective: " + adjective);
        }
        return mod;
    }

    /**
     * Get a list of all adjectives that can be applied to equipments
     * @return List<String> list of all adjectives
     */
    public static List<String> getAllAdjectivesList() {
        return new ArrayList<>(allAdjectives.keySet());
    }

    private int baseAttack;
    private int baseDefense;
    private String equipmentName;
    private final List<String> adjectives = new LinkedList<>();

    /**
     * Get the type of the equipment
     * @return EquipmentType the type of the equipment
     */
    abstract public EquipmentType getType();

    /**
     * Set the name of the equipment
     * @param name the name of the equipment
     */
    public void setName(String name) {
        this.equipmentName = name;
    }

    /**
     * Get the name of the equipment
     * @return String the name of the equipment
     */
    public String getName() {
        return this.equipmentName;
    }

    public String getFullNmae() {
        StringBuilder sb = new StringBuilder();
        if (!this.adjectives.isEmpty()) {
            for (String adjective : this.adjectives) {
                sb.append(adjective).append(", ");
            }
            // remove the last comma
            sb.deleteCharAt(sb.length() - 2);
        }

        sb.append(this.getName());
        return sb.toString();
    }

    /**
     * Set the base value of attack for the equipment
     * @param baseAttack the base value of attack
     */
    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    /**
     * Get the base value of attack for the equipment
     * @return int the base value of attack
     */
    public int getBaseAttack() {
        return this.baseAttack;
    }

    /**
     * Get the value of attack for the equipment, after applying all adjective modifiers
     * if the attack is negative, return 0
     * @return int the value of attack
     */
    public int getAttack() {
        int attack = this.baseAttack;
        for (String adjective : adjectives) {
            AdjectiveModifier mod = getAdjectiveModifier(adjective);
            attack += mod.attackModifier();
        }
        return Math.max(0, attack);
    }

    /**
     * Set the base value of defense for the equipment
     * @param baseDefense the base value of defense
     */
    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    /**
     * Get the base value of defense for the equipment
     * @return int the base value of defense
     */
    public int getBaseDefense() {
        return this.baseDefense;
    }

    /**
     * Get the value of defense for the equipment, after applying all adjective modifiers
     * negative defense increases the damage received
     * @return int the value of defense
     */
    public int getDefense() {
        int defense = this.baseDefense;
        for (String adjective : adjectives) {
            AdjectiveModifier mod = getAdjectiveModifier(adjective);
            defense += mod.defenseModifier();
        }
        return defense;
    }

    /**
     * Add an adjective to the equipment
     * if the adjective is not recognized, throw an exception
     * if the adjective is already in the list, do nothing as the effect of the same adjective does not stack
     * @param adjective the adjective to add
     */
    public void addAdjective(String adjective) {
        if (allAdjectives.containsKey(adjective)) {
            // check if the adjective is already in the list
            if (!adjectives.contains(adjective)) {
                adjectives.add(adjective);
            }
        }
        else {
            throw new IllegalArgumentException("Unrecognized adjective: " + adjective);
        }
    }

    /**
     * Add a list of adjectives to the equipment
     * @param adjectives the list of adjectives to add
     */
    public void addAdjective(List<String> adjectives) {
        for (String adjective : adjectives) {
            addAdjective(adjective);
        }
    }

    /**
     * Get the list of adjectives applied to the equipment
     * @return List<String> the list of adjectives
     */
    public List<String> getAdjectives() {
        return this.adjectives;
    }

    /**
     * Clear all adjectives from the equipment
     */
    public void clearAdjectives() {
        this.adjectives.clear();
    }

    /**
     * Combine this equipment with another equipment
     * @param other the other equipment to combine with
     * @return Equipment the resulting equipment after combination
     */
    abstract public Equipment combine(Equipment other);

    /**
     * Get the string representation of the equipment
     * @return String the string representation of the equipment
     */
    @Override
    public String toString() {
        return this.getFullNmae() + " (ATK: " + this.getAttack() + ", DEF: " + this.getDefense() + ")";
    }

    /**
     * Combine two equipments by selecting the one with higher base attack
     * if the base attack is the same, select the one with higher base defense
     * @param e1 the first equipment
     * @param e2 the second equipment
     * @return Equipment the equipment with higher base attack and defense
     */
    protected static Equipment combineBaseSelector(Equipment e1, Equipment e2) {
        if (e1.getBaseAttack() > e2.getBaseAttack()) {
            return e1;
        }
        else if (e1.getBaseAttack() < e2.getBaseAttack()) {
            return e2;
        }
        else {
            if (e1.getBaseDefense() >= e2.getBaseDefense()) {
                return e1;
            }
            else {
                return e2;
            }
        }
    }

    /**
     * Clone the equipment
     * @return Equipment the cloned equipment
     */
    public abstract Equipment clone();

    /**
     * Compare this equipment with another equipment
     * if this equipment has higher attack, return 1
     * if this equipment has lower attack, return -1
     * if the attack is the same, compare the defense
     * @param other the other equipment to compare with
     * @return int 1 if this equipment is greater, -1 if this equipment is smaller, 0 if they are equal
     */
    @Override
    public int compareTo(Equipment other) {
        if (this.getAttack() > other.getAttack()) {
            return 1;
        }
        else if (this.getAttack() < other.getAttack()) {
            return -1;
        }
        else {
            return Integer.compare(this.getDefense(), other.getDefense());
        }
    }
}