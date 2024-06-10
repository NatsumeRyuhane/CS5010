package rpg;

import java.util.*;

/**
 * A class representing a battle between two players.
 */
public class Battle {
    private final Player[] players = {null, null};
    private final List<Equipment> equipmentPool = new LinkedList<>();
    private int turn = 0;

    /**
     * Constructs a new battle between two players.
     * @param player1 the first player
     * @param player2 the second player
     * @param equipmentPool the pool of equipment available for the battle
     */
    public Battle(Player player1, Player player2, Equipment[] equipmentPool) {
        this.players[0] = player1;
        this.players[1] = player2;
        this.equipmentPool.addAll(Arrays.asList(equipmentPool));
    }

    /**
     * Removes equipment from the pool.
     * @param e the equipment to be removed
     */
    private void removeFromPool(Equipment e) {
        this.equipmentPool.remove(e);
    }

    /**
     * Runs the battle.
     */
    public void run() {
        System.out.println("Battle started!");
        System.out.println("Player 1: [" + this.players[0].getName() + "] ATK: " + this.players[0].getAttack() + " DEF: " + this.players[0].getDefense());
        System.out.println("Player 2: [" + this.players[1].getName() + "] ATK: " + this.players[1].getAttack() + " DEF: " + this.players[1].getDefense());
        System.out.println("Item Pool:");
        for (Equipment e: this.equipmentPool) {
            System.out.println("  [" + e.getFullNmae() + "] ATK: " + e.getAttack() + " DEF: " + e.getDefense());
        }
        System.out.println();

        while (!this.equipmentPool.isEmpty()) {
            System.out.println("[Turn " + (this.turn + 1) + "]");
            Player p = this.players[this.turn % 2];
            Equipment e = p.pick(this.equipmentPool.toArray(new Equipment[0]));
            p.equip(e);
            this.removeFromPool(e);
            System.out.println("Player \"" + p.getName() + "\" picked and equipped [" + e + "]");
            p.attack(this.players[(this.turn + 1) % 2]);
            System.out.println();
            this.turn++;
        }

        System.out.println("Battle ended!");
        if (this.players[0].getDamageReceived() > this.players[1].getDamageReceived()) {
            System.out.println("Player " + this.players[1].getName() + " wins!");
            System.out.println("Player " + this.players[0].getName() + " received " + this.players[0].getDamageReceived() + " damage!");
            System.out.println("Player " + this.players[1].getName() + " received " + this.players[1].getDamageReceived() + " damage!");
        } else if (this.players[0].getDamageReceived() < this.players[1].getDamageReceived()) {
            System.out.println("Player " + this.players[0].getName() + " wins!");
            System.out.println("Player " + this.players[0].getName() + " received " + this.players[0].getDamageReceived() + " damage!");
            System.out.println("Player " + this.players[1].getName() + " received " + this.players[1].getDamageReceived() + " damage!");
        } else {
            System.out.println("It's a draw!");
            System.out.println("Both players received " + this.players[0].getDamageReceived() + " damage!");
        }

        System.out.println();

        System.out.println("Player 1: [" + this.players[0].getName() + "] ATK: " + this.players[0].getAttack() + " DEF: " + this.players[0].getDefense());
        System.out.println(this.players[0]);
        System.out.println("Player 2: [" + this.players[1].getName() + "] ATK: " + this.players[1].getAttack() + " DEF: " + this.players[1].getDefense());
        System.out.println(this.players[1]);
    }
}
