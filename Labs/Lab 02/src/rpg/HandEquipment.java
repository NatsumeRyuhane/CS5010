package rpg;

import java.util.List;

public class HandEquipment extends Equipment {
    public HandEquipment(String name, int baseAttack) {
        this.setName(name);
        this.setBaseAttack(baseAttack);
    }

    public HandEquipment(String name, int baseAttack, String adjective) {
        this.setName(name);
        this.addAdjective(adjective);
        this.setBaseAttack(baseAttack);
    }

    public HandEquipment(String name, int baseAttack, List<String> adjectives) {
        this.setName(name);
        this.addAdjective(adjectives);
        this.setBaseAttack(baseAttack);
    }

    /**
     * Hand equipments are only used for attack
     * The defense value will be overridden to 0
     */
    @Override
    public int getDefense() {
        return 0;
    }

    @Override
    public Equipment combine(Equipment other) {
        if (other instanceof HandEquipment otherHand) {
            HandEquipment newHand = new HandEquipment("", 0);

            newHand.addAdjective(this.getAdjectives());
            newHand.addAdjective(otherHand.getAdjectives());
            newHand.setBaseAttack(this.getBaseAttack() + otherHand.getBaseAttack());
            newHand.setBaseDefense(0);
            newHand.setName(Equipment.combineBaseSelector(this, other).getName());

            return newHand;
        } else {
            throw new IllegalArgumentException("Cannot combine hand equipment with other equipment types");
        }
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.HAND;
    }

    @Override
    public Equipment clone() {
        return new HandEquipment(this.getName(), this.getBaseAttack(), this.getAdjectives());
    }
}
