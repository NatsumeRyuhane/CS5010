package test;
import dungeon.*;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;



public class MedievalLevelBuilderTest {
    @Test
    public void testConstructor() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMaxRooms() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, -5, 10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMaxMonsters() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, -10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMaxTreasure() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, -15);
    }

    @Test
    public void testAddRoom() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
    }

    @Test
    public void testAddRoom2() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addRoom("Another room");
        b.addRoom("Yet another room");
        b.addRoom("One more room");
        b.addRoom("The last room");
    }

    @Test(expected = IllegalStateException.class)
    public void testAddRoomInvalid() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addRoom("Another room");
        b.addRoom("Yet another room");
        b.addRoom("One more room");
        b.addRoom("The last room");
        b.addRoom("Too many rooms");
    }

    @Test(expected = IllegalStateException.class)
    public void testAddRoomTooMany() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 1, 10, 15);
        b.addRoom("A room");
        b.addRoom("Another room");
    }

    @Test
    public void testAddGoblins() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addGoblins(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoblinsInvalidRoom() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addGoblins(1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoblinsInvalidRoom2() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addRoom("Another room");
        b.addGoblins(2, 5);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddGoblinsTooMany() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addGoblins(0, 15);
    }

    // test addOrc
    @Test
    public void testAddOrcs() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 1, 1, 0);
        b.addRoom("A room");
        b.addOrc(0);
        assertTrue(b.build().toString().contains("Orc"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOrcInvalidRoom() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addOrc(1);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddOrcTooMany() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 1, 15);
        b.addRoom("A room");
        b.addOrc(0);
        b.addOrc(0);
    }

    @Test
    public void testAddTreasure() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 1, 0, 1);
        b.addRoom("A room");
        b.addPotion(0);
        assertTrue(b.build().toString().contains("healing potion"));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddTreasureTooMany() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 1);
        b.addRoom("A room");
        b.addPotion(0);
        b.addPotion(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTreasureInvalidRoom() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addPotion(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTreasureInvalidRoom2() {
        MedievalLevelBuilder b = new MedievalLevelBuilder(1, 5, 10, 15);
        b.addRoom("A room");
        b.addRoom("Another room");
        b.addPotion(-1);
    }
}
