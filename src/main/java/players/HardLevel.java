package players;

import gameservice.GameBoard;
import gameservice.MinimaxClass;

public final class HardLevel extends Player {



    public HardLevel(String name, char valueToMove, char valueOfEnemy) {
        super(name, valueToMove, valueOfEnemy);
    }


    @Override
    public int[] makesMove(GameBoard gameBoard) throws InterruptedException {
        //MinimaxClass.initializePlayers(this.getValueToMove(), this.getValueOfEnemy());
        return MinimaxClass.findBestMove(gameBoard, getValueToMove(), getValueOfEnemy());
    }
}
