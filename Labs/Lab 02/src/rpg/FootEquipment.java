package rpg;

import java.util.List;

public class FootEquipment extends Equipment {
    public FootEquipment(String name, int baseAttack, int baseDefense) {
        this.setName(name);
        this.setBaseAttack(baseAttack);
        this.setBaseDefense(baseDefense);
    }

    public FootEquipment(String name, int baseAttack, int baseDefense, String adjective) {
        this.setName(name);
        this.addAdjective(adjective);
        this.setBaseAttack(baseAttack);
        this.setBaseDefense(baseDefense);
    }

    public FootEquipment(String name, int baseAttack, int baseDefense, List<String> adjectives) {
        this.setName(name);
        this.addAdjective(adjectives);
        this.setBaseAttack(baseAttack);
        this.setBaseDefense(baseDefense);
    }

    @Override
    public Equipment combine(Equipment other) {
        if (other instanceof FootEquipment otherFoot) {
            FootEquipment newFoot = new FootEquipment("", 0, 0);

            newFoot.addAdjective(this.getAdjectives());
            newFoot.addAdjective(otherFoot.getAdjectives());
            newFoot.setBaseAttack(this.getBaseAttack() + otherFoot.getBaseAttack());
            newFoot.setBaseDefense(this.getBaseDefense() + otherFoot.getBaseDefense());
            newFoot.setName(Equipment.combineBaseSelector(this, other).getName());

            return newFoot;
        } else {
            throw new IllegalArgumentException("Cannot combine foot equipment with other equipment types");
        }
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.FOOT;
    }

    @Override
    public Equipment clone() {
        return new FootEquipment(this.getName(), this.getBaseAttack(), this.getBaseDefense(), this.getAdjectives());
    }
}
