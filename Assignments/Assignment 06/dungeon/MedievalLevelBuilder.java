package dungeon;

public class MedievalLevelBuilder {
    private final Level level;
    private final int maxRooms;
    private final int maxMonsters;
    private final int maxTreasure;

    private int roomsAdded = 0;
    private int monstersAdded = 0;
    private int treasureAdded = 0;

    public MedievalLevelBuilder(int level, int maxRooms, int maxMonsters, int maxTreasure) {
        if (maxRooms < 0) {
            throw new IllegalArgumentException("maxRooms must be positive");
        }

        if (maxMonsters < 0) {
            throw new IllegalArgumentException("maxMonsters must be positive");
        }

        if (maxTreasure < 0) {
            throw new IllegalArgumentException("maxTreasure must be positive");
        }

        this.maxRooms = maxRooms;
        this.maxMonsters = maxMonsters;
        this.maxTreasure = maxTreasure;

        this.level = new Level(level);
    }

    public void addRoom(String description) {
        if (roomsAdded < maxRooms) {
            this.level.addRoom(description);
            this.roomsAdded++;
        } else {
            throw new IllegalStateException("Unable to add more rooms to this level");
        }
    }

    public void addGoblins(int room, int amount) {
        String description = "mischievous and very unpleasant, vengeful, and greedy creature whose primary purpose is to cause trouble to humankind";
        if (this.monstersAdded + amount <= this.maxMonsters) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                for (int i = 0; i < amount; i++) {
                    this.level.addMonster(room, new Monster("goblin", description, 7));
                    this.monstersAdded += 1;
                }

            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more monsters to this level");
        }
    }

    public void addOrc(int room) {
        String description = "brutish, aggressive, malevolent being serving evil";
        if (this.monstersAdded < this.maxMonsters) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addMonster(room, new Monster("orc", description, 20));
                this.monstersAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more monsters to this level");
        }
    }

    public void addOgre(int room) {
        String description = "large, hideous man-like being that likes to eat humans for lunch";
        if (this.monstersAdded < this.maxMonsters) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addMonster(room, new Monster("ogre", description, 50));
                this.monstersAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more monsters to this level");
        }
    }

    public void addHuman(int room, String name, String description, int hitPoints) {
        if (this.monstersAdded < this.maxMonsters) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addMonster(room, new Monster(name, description, hitPoints));
                this.monstersAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more monsters to this level");
        }
    }

    public void addPotion(int room) {
        if (this.treasureAdded < this.maxTreasure) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addTreasure(room, new Treasure("a healing potion", 1));
                this.treasureAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more treasure to this level");
        }
    }

    public void addGold(int room, int amount) {
        if (this.treasureAdded < this.maxTreasure) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addTreasure(room, new Treasure("pieces of gold", amount));
                this.treasureAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more treasure to this level");
        }
    }

    public void addWeapon(int room, String description) {
        if (this.treasureAdded < this.maxTreasure) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addTreasure(room, new Treasure(description, 10));
                this.treasureAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more treasure to this level");
        }
    }

    public void addSpecial(int room, String description, int value) {
        if (this.treasureAdded < this.maxTreasure) {
            try{
                if (room < 0 || room >= this.roomsAdded) {
                    throw new IllegalArgumentException("Target room does not exist");
                }

                this.level.addTreasure(room, new Treasure(description, value));
                this.treasureAdded++;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Target room does not exist");
            }
        } else {
            throw new IllegalStateException("Unable to add more treasure to this level");
        }
    }

    public Level build() {
        if (this.roomsAdded < this.maxRooms) {
            throw new IllegalStateException("Not enough rooms added to this level");
        }

        if (this.monstersAdded < this.maxMonsters) {
            throw new IllegalStateException("Not enough monsters added to this level");
        }

        if (this.treasureAdded < this.maxTreasure) {
            throw new IllegalStateException("Not enough treasure added to this level");
        }

        return this.level;
    }
}
