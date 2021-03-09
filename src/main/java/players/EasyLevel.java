package players;


import gameservice.Console;
import gameservice.GameBoard;

import java.util.Random;

//class for AI level easy - just plays random coordinates
public final class EasyLevel extends Player {
    public EasyLevel (String name, char valueToMove, char valueOfEnemy) {
        super(name, valueToMove, valueOfEnemy);
    }

    public void tellThatIMakeMove () {
        Console.displayThatAIMakesMove(this);
    }

    @Override
    public int[] makesMove(GameBoard gameBoard) throws InterruptedException {
        //Thread.sleep(1000);
        tellThatIMakeMove();
        Random random = new Random();
        int[] inputCoordinates = new int[2];
        while (true) {
            inputCoordinates[0] = random.nextInt(3 - 1 + 1) + 1 -1; //-1 because index from 0 to 2
            inputCoordinates[1] = random.nextInt(3 - 1 + 1) + 1 -1; //-1 because index from 0 to 2
            if (gameBoard.ifCellIsOccupied(inputCoordinates[0], inputCoordinates[1])) {
                //repeat the loop, since the cell is occupied
            } else {
                return inputCoordinates; //else return the coordinate to fill in the cell
            }
        }
    }

}
