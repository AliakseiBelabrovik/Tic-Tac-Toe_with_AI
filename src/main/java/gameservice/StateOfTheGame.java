package gameservice;

import players.*;


//class state of the game starts the game and checks who has won
//coordinates the game with other classes
public final class StateOfTheGame {

    //menu loop to start the game: accepts two commands: start and exit
    //then, creates two objects and playes the game
    //if input is exit - stops the loop and the game
    public static void startTheGame () throws InterruptedException {
        String[] input;
        PlayerFactory playerFactory = new PlayerFactory();
        while (true) {
            //ask user for a command - exit or start
            input = Console.askForACommand();
            //if command is "exit", stop the game
            if (input[0].equals("exit"))  {
                break;

            } else {

                GameBoard gameBoard = new GameBoard();
                Player player1 = playerFactory.getPlayer(input[1], 'X', 'O');
                Player player2 = playerFactory.getPlayer(input[2], 'O','X');


                //start playing the game
                playingTheGame(gameBoard, player1, player2);
            }
        }
    }


    //gets gameBoard and two players, plays game as an infinit loop which stops when smb wins or draw
    public static void playingTheGame(GameBoard gameBoard, Player player1, Player player2) throws InterruptedException {
            while (true) {
               //the first plays has to move
                gameBoard.setValueToEmptyCell(player1.getValueToMove(), player1.makesMove(gameBoard));

                //check if won
                if (checkIfWon(gameBoard))
                    break;
                //the first plays has to move
                gameBoard.setValueToEmptyCell(player2.getValueToMove(), player2.makesMove(gameBoard));
                //check if won
                if (checkIfWon(gameBoard))
                    break;
            }
    }


    public static boolean checkIfWon(GameBoard gameBoard) {


        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (gameBoard.getValueFromIndex(row, 0) == gameBoard.getValueFromIndex(row, 1) &&
                    gameBoard.getValueFromIndex(row, 1) == gameBoard.getValueFromIndex(row, 2)) {
                if (gameBoard.getValueFromIndex(row, 0) == 'X') {
                    Console.displayWhoHasWon('X');
                    return true;
                } else if (gameBoard.getValueFromIndex(row, 0) == 'O') {
                    Console.displayWhoHasWon('O');
                    return true;
                }
            }
        }


        // Checking for Columns for X or O victory.
        for (int column = 0; column < 3; column++)
        {
            if (gameBoard.getValueFromIndex(0, column) == gameBoard.getValueFromIndex(1, column) &&
                    gameBoard.getValueFromIndex(1, column) == gameBoard.getValueFromIndex(2, column)) {
                if (gameBoard.getValueFromIndex(0, column) == 'X') {
                    Console.displayWhoHasWon('X');
                    return true;
                } else if (gameBoard.getValueFromIndex(0, column) == 'O') {
                    Console.displayWhoHasWon('O');
                    return true;
                }
            }
        }


        //check the second diagonal
        if (gameBoard.getValueFromIndex(2,0) == gameBoard.getValueFromIndex(1, 1) &&
                    gameBoard.getValueFromIndex(1, 1) == gameBoard.getValueFromIndex(0, 2))
        {
            if (gameBoard.getValueFromIndex(2, 0) == 'X') {
                Console.displayWhoHasWon('X');
                return true;
            } else if (gameBoard.getValueFromIndex(2, 0) == 'O') {
                Console.displayWhoHasWon('O');
                return true;
            }
        }


        //check the main diagonal
        if (gameBoard.getValueFromIndex(0, 0) == gameBoard.getValueFromIndex(1, 1) &&
                gameBoard.getValueFromIndex(1, 1) == gameBoard.getValueFromIndex(2, 2))
        {
            if (gameBoard.getValueFromIndex(0, 0) == 'X') {
                Console.displayWhoHasWon('X');
                return true;
            } else if (gameBoard.getValueFromIndex(0, 0) == 'O') {
                Console.displayWhoHasWon('O');
                return true;
            }

        }


        //check if draw
        if (checkIfDraw(gameBoard))  {
            Console.displayIfDraw();
            return true;
        }

        //if no check returns true, return false (i.e. the game is not finished)
        return false;

    }

    public static boolean checkIfDraw (GameBoard gameBoard) {
        for (char[] row : gameBoard.getBoard()) {
            for (int j = 0; j < gameBoard.getBoard().length; j++) {
                if (row[j] == ' ') //if any cell is empty - return false
                    return false;
            }
        }
        //if all the cells are occupied return true (i.e. draw)
        return true;
    }



    //check if there are two in rows with my X or O - then I wim
    public static int[] checkIfTwoInARowToWin(GameBoard gameBoard, char valueToMove) {
        //System.out.println("I am in a checkIfTwoInARow method");
        int benchmark = Character.getNumericValue(valueToMove) * 2;
        int[] coordinatesOfEmptyCell;
        //check the rows
        int rows = 0;
        for (int i = 0; i < gameBoard.getBoard().length; i++) {
            for (int j = 0; j < gameBoard.getBoard().length; j++) {
                if(Character.getNumericValue(gameBoard.getValueFromIndex(i,j)) == -1) {
                    rows += 0;
                } else {
                    rows += Character.getNumericValue(gameBoard.getValueFromIndex(i,j));
                }
                if (rows == benchmark) {
                    //System.out.println("There are two in a row, start looking empty cell");
                    coordinatesOfEmptyCell = gameBoard.findEmptyCellInARow(i);

                    //if there is an empty cell - return it
                    if (coordinatesOfEmptyCell[0] != 5 && coordinatesOfEmptyCell[1] != 5) {
                        //System.out.println("There is an empty cell");
                        return coordinatesOfEmptyCell;
                    }

                    //if the cell is not empty - go further with the check
                }
            }
            rows = 0;
        }

        //check the columns
        int columns = 0;
        int j = 0;
        while (j <= 2) {
            for (int i = 0; i < gameBoard.getBoard().length; i ++) {
                if(Character.getNumericValue(gameBoard.getValueFromIndex(i,j)) == -1) {
                    columns += 0;
                } else {
                    columns += Character.getNumericValue(gameBoard.getValueFromIndex(i,j));
                }
                if (columns == benchmark) {
                    //System.out.println("There are two in a column, start looking empty cell");
                    coordinatesOfEmptyCell = gameBoard.findEmptyCellInAColumn(j);

                    //if there is an empty cell - return it
                    if (coordinatesOfEmptyCell[0] != 5 && coordinatesOfEmptyCell[1] != 5) {
                        //System.out.println("There is an empty cell");
                        return coordinatesOfEmptyCell;
                    }
                }
            }
            j++;
            columns = 0;
        }

        //check the second diagonale of matrix
        int secondDiagonale = 0;
        int i = 2;
        j = 0;
        while (i>=0) { //go from i=2, j=0 to i=0,j=2

            if(Character.getNumericValue(gameBoard.getValueFromIndex(i,j)) == -1) {
                secondDiagonale += 0;
            } else {
                secondDiagonale += Character.getNumericValue(gameBoard.getValueFromIndex(i,j));
            }
            if (secondDiagonale == benchmark) {
                //System.out.println("There are two in a second diagonale, start looking empty cell");
                coordinatesOfEmptyCell = gameBoard.findEmptyCellInADiagonale("second");
                //if there is an empty cell - return it
                if (coordinatesOfEmptyCell[0] != 5 && coordinatesOfEmptyCell[1] != 5) {
                    //System.out.println("There is an empty cell");
                    return coordinatesOfEmptyCell;
                }
            }
            i--;
            j++;
        }

        //check the main diagonale of matrix
        i = 0;
        j = 0;
        int mainDiagonale = 0;
        while (i<=2) { //go from i=0, j=0 to i=2,j=2
            if (Character.getNumericValue(gameBoard.getValueFromIndex(i,j)) == -1) {
            } else {
                mainDiagonale += Character.getNumericValue(gameBoard.getValueFromIndex(i,j));
            }
            if (mainDiagonale == benchmark) {
                //System.out.println("There are two in a main diagonale, start looking empty cell");
                coordinatesOfEmptyCell = gameBoard.findEmptyCellInADiagonale("main");
                //if there is an empty cell - return it
                if (coordinatesOfEmptyCell[0] != 5 && coordinatesOfEmptyCell[1] != 5) {
                    //System.out.println("There is an empty cell");
                    return coordinatesOfEmptyCell;
                }

            }
            i++;
            j++;
        }
        //System.out.println("There were no two in a row or no empty cell");
        coordinatesOfEmptyCell = new int[]{4, 4};
        //if no row with two, return coordinates 4, 4
        return coordinatesOfEmptyCell;
    }
}
