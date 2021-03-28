package players;

/**
 * Factory pattern to create a player of concrete class to play with
 */
public class PlayerFactory {

    public Player getPlayer(String player, char valueToMove, char valueOfEnemy) {
        if (player.equalsIgnoreCase("easy")) {
            return new EasyLevel(player, valueToMove, valueOfEnemy);
        } else if (player.equalsIgnoreCase("medium")) {
            return new MediumLevel(player, valueToMove, valueOfEnemy);
        } else if (player.equalsIgnoreCase("hard")) {
            return new HardLevel(player, valueToMove, valueOfEnemy);
        } else {
            return new User(player, valueToMove, valueOfEnemy);
        }
    }


}
