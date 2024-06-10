package rpg;

import java.util.List;

public class HeadEquipment extends Equipment {

    public HeadEquipment(String name, int baseDefense) {
        this.setName(name);
        this.setBaseDefense(baseDefense);
    }

    public HeadEquipment(String name, int baseDefense, String adjective) {
        this.setName(name);
        this.addAdjective(adjective);
        this.setBaseDefense(baseDefense);
    }

    public HeadEquipment(String name, int baseDefense, List<String> adjectives) {
        this.setName(name);
        this.addAdjective(adjectives);
        this.setBaseDefense(baseDefense);
    }

    /**
     * Head equipments are only used for defense
     * The attack value will be overridden to 0
     */
    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public Equipment combine(Equipment other) {
        if (other instanceof HeadEquipment otherHead) {
            HeadEquipment newHead = new HeadEquipment("", 0);

            newHead.addAdjective(this.getAdjectives());
            newHead.addAdjective(otherHead.getAdjectives());
            newHead.setBaseDefense(this.getBaseDefense() + otherHead.getBaseDefense());
            newHead.setBaseAttack(0);
            newHead.setName(Equipment.combineBaseSelector(this, other).getName());

            return newHead;
        } else {
            throw new IllegalArgumentException("Cannot combine head equipment with other equipment types");
        }
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.HEAD;
    }

    @Override
    public Equipment clone() {
          return new HeadEquipment(this.getName(), this.getBaseDefense(), this.getAdjectives());
    }
}