package players;


import gameservice.Console;
import gameservice.GameBoard;
import gameservice.StateOfTheGame;

import java.util.Random;

public final class MediumLevel extends Player{

    public MediumLevel(String name, char valueToMove, char valueOfEnemy) {
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
        int[] inputCoordinates;

        //first check if there are two in a row to win - and return the empty cell
        //System.out.println("Start looking for two in a row");
        inputCoordinates = StateOfTheGame.checkIfTwoInARowToWin(gameBoard, this.getValueToMove());
        //System.out.println(" let see if I found smth");
        if (inputCoordinates[0] != 4 && inputCoordinates[1] != 4) {
            //System.out.println("Yes I found an empty cell with two in a row to win");
            return inputCoordinates;
        }
        //if cannot win, check if can block
        inputCoordinates = StateOfTheGame.checkIfTwoInARowToWin(gameBoard, this.getValueOfEnemy());
        //inputCoordinates = StateOfTheGame.checkIfTwoInARowToBlock(gameBoard, this.valueOfEnemy);
        if (inputCoordinates[0] != 4 && inputCoordinates[1] != 4){
            //check if can block
            //System.out.println("Yes I found an empty cell with two in a row to block");
            return inputCoordinates;

        } else {
            //if not, move randomly
            //System.out.println("I did not found two in a row with a third empty cell");
            while (true) {
                //System.out.println("I am in a while loop for random coordinates");
                //if nowhere, move randomly
                inputCoordinates[0] = random.nextInt(3 - 1 + 1) + 1 - 1; //-1 because index from 0 to 2
                inputCoordinates[1] = random.nextInt(3 - 1 + 1) + 1 - 1; //-1 because index from 0 to 2
                if (gameBoard.ifCellIsOccupied(inputCoordinates[0], inputCoordinates[1])) {
                    //repeat the loop, since the cell is occupied
                } else {
                    return inputCoordinates; //else return the coordinate to fill in the cell
                }
            }
        }
    }
}