package test;

import org.junit.Test;

import rpg.*;

import static org.junit.Assert.*;

public class TestPlayer {
    @Test
    public void testPlayer() {
        Player p = new Player("p");
        assertEquals(10, p.getAttack());
        assertEquals(10, p.getDefense());
    }

    @Test
    public void testPlayerWithEquipment() {
        Player p = new Player("p");
        p.equip(new HandEquipment("Sword", 10));
        assertEquals(20, p.getAttack());
        assertEquals(10, p.getDefense());
    }

    @Test
    public void testEquipmentAutoCombine() {
        Player p = new Player("p");
        p.equip(new HandEquipment("Sword", 10));
        p.equip(new HandEquipment("Sword", 10));
        assertEquals(30, p.getAttack());
        assertEquals(10, p.getDefense());
    }

    @Test
    public void testEquipmentAutoCombineWithAdjective() {
        Player p = new Player("p");
        Equipment e1 = new HeadEquipment("HE", 10);
        e1.addAdjective("Shiny");
        p.equip(e1);
        Equipment e2 = new HeadEquipment("HE", 10);
        e2.addAdjective("Light");
        p.equip(e2);
        String name = p.getEquipment()[0].getFullNmae();
        assertTrue(name.contains("Shiny"));
        assertTrue(name.contains("Light"));
        assertEquals(
                p.getBaseDefense() + p.getEquipment()[0].getDefense(), p.getDefense());
    }

    @Test
    public void testEquipmentPick() {
        Player p = new Player("p");
        p.equip(new HeadEquipment("Helmet", 10));
        p.equip(new HandEquipment("Sword", 10));
        p.equip(new HandEquipment("Shield", 10));
        p.equip(new FootEquipment("Boots", 10, 10));

        Equipment[] pool = EquipmentFactory.createRandomEquipment(100);
        Equipment picked = p.pick(pool);
        assertEquals(EquipmentType.FOOT, picked.getType());
    }

    @Test
    public void testEquipmentPick2() {
        Player p = new Player("p");
        p.equip(new HandEquipment("Sword", 10));
        p.equip(new HandEquipment("Shield", 10));
        p.equip(new FootEquipment("Boots", 10, 10));

        Equipment[] pool = {
            new HeadEquipment("Helmet", 10),
            new HandEquipment("Sword", 10),
            new HandEquipment("Shield", 11),
            new FootEquipment("Boots", 12, 11),
            new FootEquipment("Sneakers", 13, 12)
        };

        Equipment picked = p.pick(pool);
        assertEquals(picked, pool[4]);

        p.equip(picked);
        picked = p.pick(pool);
        assertEquals(picked, pool[0]);
    }

    @Test
    public void testTakeDamage() {
        Player p = new Player("p");
        p.damage(15);
        assertEquals(5, p.getDamageReceived());

        p.equip(new HeadEquipment("Helmet", 10));
        p.damage(15);
        assertEquals(5, p.getDamageReceived());

        p.damage(25);
        assertEquals(10, p.getDamageReceived());
    }
}
