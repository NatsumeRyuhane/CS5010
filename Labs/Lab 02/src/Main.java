import rpg.*;
public class Main {
    public static void main(String[] args) {

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Equipment[] equipments = EquipmentFactory.createRandomEquipment(10);
        Battle b = new Battle(player1, player2, equipments);
        b.run();

        /**
         * Note
         * If player 1 won with taken 0 damage, this is not very common, but not a bug
         * This can happen if player 1 happens to pickup a good equipment that have a high defense,
         * and keeps his defense high throughout the battle
         * (player deals 0 damage if their attack is less than the other player's defense)
         */
    }
}