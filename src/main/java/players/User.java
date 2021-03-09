package players;


import gameservice.Console;
import gameservice.GameBoard;

public final class User extends Player{
    public User (String name, char valueToMove, char valueOfEnemy) {
        super(name, valueToMove, valueOfEnemy);
    }

    @Override
    public int[] makesMove(GameBoard gameBoard) {
        return Console.askForCoordinatesToMove(gameBoard);
    }


}
