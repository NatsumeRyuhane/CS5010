package test;

import com.sun.source.tree.AssertTree;
import org.junit.Test;
import rpg.EquipmentFactory;
import rpg.Equipment;
import org.junit.Assert;
public class TestEquipmentFactory {
    @Test
    public void testEquipmentFactory() {
        Equipment e = EquipmentFactory.createRandomEquipment();
        Assert.assertNotNull(e);
    }

    @Test
    public void testEquipmentFactoryBulk() {
        Equipment[] e = EquipmentFactory.createRandomEquipment(10);
        Assert.assertEquals(10, e.length);
        for (Equipment equipment : e) {
            Assert.assertNotNull(equipment);
        }
    }
}
