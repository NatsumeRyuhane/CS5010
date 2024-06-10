package test;
import org.junit.Test;
import rpg.Equipment;
import rpg.FootEquipment;
import rpg.HandEquipment;

public class TestEquipment {
    @Test
    public void testEquipmentConstructor() {
        Equipment e = new HandEquipment("Sword", 10);
        assert e.getName().equals("Sword");
        assert e.getAttack() == 10;
        assert e.getDefense() == 0;
    }

    @Test
    public void testEquipmentWithModifier() {
        Equipment e = new FootEquipment("Boots", 10, 10);
        String adj = (String) Equipment.getAllAdjectivesList().toArray()[0];
        e.addAdjective(adj);
        assert(e.getAttack() - e.getBaseAttack() ==
                Equipment.getAdjectiveModifier(adj).attackModifier());
        assert(e.getDefense() - e.getBaseDefense() ==
                Equipment.getAdjectiveModifier(adj).defenseModifier());
    }

    @Test
    public void testEquipmentWithMultipleModifier() {
        Equipment e = new FootEquipment("Boots", 10, 10);
        String adj1 = (String) Equipment.getAllAdjectivesList().toArray()[0];
        String adj2 = (String) Equipment.getAllAdjectivesList().toArray()[1];
        e.addAdjective(adj1);
        e.addAdjective(adj2);
        assert(e.getAttack() - e.getBaseAttack() ==
                Equipment.getAdjectiveModifier(adj1).attackModifier() +
                Equipment.getAdjectiveModifier(adj2).attackModifier());
        assert(e.getDefense() - e.getBaseDefense() ==
                Equipment.getAdjectiveModifier(adj1).defenseModifier() +
                Equipment.getAdjectiveModifier(adj2).defenseModifier());
    }

    @Test
    public void testEquipmentCombine() {
        Equipment e1 = new FootEquipment("Boots", 5, 10);
        Equipment e2 = new FootEquipment("Sneakers", 10, 10);
        String adj1 = (String) Equipment.getAllAdjectivesList().toArray()[0];
        String adj2 = (String) Equipment.getAllAdjectivesList().toArray()[1];
        e1.addAdjective(adj1);
        e2.addAdjective(adj2);
        Equipment e3 = e1.combine(e2);
        assert(e3.getAttack() - e3.getBaseAttack() ==
                e1.getAttack() - e1.getBaseAttack() +
                e2.getAttack() - e2.getBaseAttack());
        assert(e3.getDefense() - e3.getBaseDefense() ==
                e1.getDefense() - e1.getBaseDefense() +
                e2.getDefense() - e2.getBaseDefense());

        // test name construction
        assert(e3.getName().contains(adj1));
        assert(e3.getName().contains(adj2));
        assert(e3.getName().contains(e2.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncompatibleCombine() {
        Equipment e1 = new FootEquipment("Boots", 5, 10);
        Equipment e2 = new HandEquipment("Sword", 10);
        e1.combine(e2);
    }
}
