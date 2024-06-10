package rpg;

import java.util.*;

/**
 * Player class represents a player in the RPG game.
 * A player has a name, base attack, base defense, and total damage received.
 */
public class Player {
    public static int PLAYER_ATTACK_BASELINE = 10;
    public static int PLAYER_DEFENSE_BASELINE = 10;
    private final String name;
    private final int attack;
    private final int defense;
    private int damageReceived;
    private final HeadEquipment[] headEquipment = {null};
    private final HandEquipment[] handEquipment = {null, null};
    private final FootEquipment[] footEquipment = {null, null};

    /**
     * Constructs a new player with the given name, using the default base attack and defense values.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.attack = PLAYER_ATTACK_BASELINE;
        this.defense = PLAYER_DEFENSE_BASELINE;
        this.damageReceived = 0;
    }

    /**
     * Constructs a new player with the given name, using the given base attack and defense values.
     * @param name the name of the player
     * @param baseAttackOverride the base attack value to use
     * @param baseDefenseOverride the base defense value to use
     */
    public Player(String name, int baseAttackOverride, int baseDefenseOverride) {
        this.name = name;
        this.attack = baseAttackOverride;
        this.defense = baseDefenseOverride;
        this.damageReceived = 0;
    }

    /**
     * Gets the base attack value of the player.
     * @return the base attack value
     */
    public int getBaseAttack() {
        return this.attack;
    }

    /**
     * Gets the base defense value of the player.
     * @return the base defense value
     */
    public int getBaseDefense() {
        return this.defense;
    }

    /**
     * Gets the total attack value of the player, including equipment bonuses.
     * @return the total attack value
     */
    public int getAttack() {
        int attack = this.getBaseAttack();

        for (HeadEquipment e : this.headEquipment) {
            if (e != null) {
                attack += e.getAttack();
            }
        }

        for (HandEquipment e : this.handEquipment) {
            if (e != null) {
                attack += e.getAttack();
            }
        }

        for (FootEquipment e : this.footEquipment) {
            if (e != null) {
                attack += e.getAttack();
            }
        }

        return attack;
    }

    /**
     * Gets the total defense value of the player, including equipment bonuses.
     * @return the total defense value
     */
    public int getDefense() {
        int defense = this.getBaseDefense();

        for (HeadEquipment e : this.headEquipment) {
            if (e != null) {
                defense += e.getDefense();
            }
        }

        for (HandEquipment e : this.handEquipment) {
            if (e != null) {
                defense += e.getDefense();
            }
        }

        for (FootEquipment e : this.footEquipment) {
            if (e != null) {
                defense += e.getDefense();
            }
        }

        return defense;
    }

    /**
     * Equips the given equipment to the player.
     * @param e the equipment to equip
     */
    public void equip(Equipment e) {
        if (e instanceof HeadEquipment) {
            if (this.headEquipment[0] == null) {
                this.headEquipment[0] = (HeadEquipment) e;
            }
            else {
                this.headEquipment[0] = (HeadEquipment) this.headEquipment[0].combine(e);
            }
        }
        else if (e instanceof HandEquipment) {
            for (int i = 0; i < this.handEquipment.length; i++) {
                if (this.handEquipment[i] == null) {
                    this.handEquipment[i] = (HandEquipment) e;
                    return;
                }
            }

            HandEquipment newHand = (HandEquipment) this.handEquipment[0].combine(e);
            this.handEquipment[0] = this.handEquipment[1];
            this.handEquipment[1] = newHand;
        }

        else if (e instanceof FootEquipment) {
            for (int i = 0; i < this.footEquipment.length; i++) {
                if (this.footEquipment[i] == null) {
                    this.footEquipment[i] = (FootEquipment) e;
                    return;
                }
            }

            FootEquipment newFoot = (FootEquipment) this.footEquipment[0].combine(e);
            this.footEquipment[0] = this.footEquipment[1];
            this.footEquipment[1] = newFoot;
        }
    }

    /**
     * Gets the equipment equipped by the player.
     * @return the equipment equipped by the player
     */
    public Equipment[] getEquipment() {
        Equipment[] equipment = new Equipment[5];
        equipment[0] = this.headEquipment[0];
        equipment[1] = this.handEquipment[0];
        equipment[2] = this.handEquipment[1];
        equipment[3] = this.footEquipment[0];
        equipment[4] = this.footEquipment[1];
        return equipment;
    }

    /**
     * Gets the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Attacks the other player.
     * @param other the player to attack
     */
    public void attack(Player other) {
        System.out.println(this.name + " attacks " + other.getName() + " for " + this.getAttack() + " ATK!");
        other.damage(this.getAttack());
    }

    /**
     * Receives damage from an attack.
     * @param attackPower the power of the attack
     */
    public void damage(int attackPower) {
        this.damageReceived += Math.max(0, attackPower - this.getDefense());
        System.out.println(this.name + " receives " + Math.max(0, attackPower - this.getDefense()) + " damage!");
        System.out.println(this.name + " now has a total of " + this.damageReceived + " damage received!");
    }

    /**
     * Gets the total damage received by the player.
     * @return the total damage received
     */
    public int getDamageReceived() {
        return this.damageReceived;
    }

    /**
     * Picks an equipment from the pool of available equipment.
     * Player prefers to pick equipment that fills the empty slots first.
     * If all slots are filled, player picks the equipment with the highest attack value,
     * if there are multiple, pick the one with the highest defense value.
     * @param equipmentPool the pool of available equipment
     * @return the equipment to pick
     */
    public Equipment pick(Equipment[] equipmentPool) {
        Equipment[] playerEquipments = this.getEquipment();

        // construct a map of equipment type to equipment list
        // This map groups the contents of the equipment pool by type
        Map<EquipmentType, List<Equipment>> categorizedEquipmentMap = new HashMap<>();
        for (EquipmentType type: EquipmentType.values()) {
            categorizedEquipmentMap.put(type, new LinkedList<>());
        }

        for (Equipment e: equipmentPool) {
            EquipmentType type = e.getType();
            categorizedEquipmentMap.get(type).add(e);
        }

        List<Equipment> filteredEquipmentPool = new LinkedList<>();


        if (playerEquipments[0] == null) {
            filteredEquipmentPool.addAll(categorizedEquipmentMap.get(EquipmentType.HEAD));
        }

        if (playerEquipments[1] == null || playerEquipments[2] == null) {
            filteredEquipmentPool.addAll(categorizedEquipmentMap.get(EquipmentType.HAND));
        }

        if (playerEquipments[3] == null || playerEquipments[4] == null) {
            filteredEquipmentPool.addAll(categorizedEquipmentMap.get(EquipmentType.FOOT));
        }

        // if filtering by type yields no result, add all equipments to the filtered pool
        if (filteredEquipmentPool.isEmpty()) {
            filteredEquipmentPool = Arrays.asList(equipmentPool);
        }


        // sort equipments in the filtered pool
        Equipment[] sortedEquipmentPool = filteredEquipmentPool.toArray(new Equipment[0]);
        Arrays.sort(sortedEquipmentPool);
        return sortedEquipmentPool[sortedEquipmentPool.length - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(this.name).append("\n");
        sb.append("Attack: ").append(this.getAttack()).append("\n");
        sb.append("Defense: ").append(this.getDefense()).append("\n");
        sb.append("Damage Received: ").append(this.damageReceived).append("\n");
        sb.append("Head Equipment: ").append(this.headEquipment[0]).append("\n");
        sb.append("Hand Equipment: ").append(this.handEquipment[0]).append(" | ").append(this.handEquipment[1]).append("\n");
        sb.append("Foot Equipment: ").append(this.footEquipment[0]).append(" | ").append(this.footEquipment[1]).append("\n");
        return sb.toString();
    }
}