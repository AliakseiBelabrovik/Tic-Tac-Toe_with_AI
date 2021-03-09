package gameservice;

public final class MinimaxClass {
    private static char currentPlayer;
    private static char opponent;


    //Evaluation function for checking the terminal state and returning the value of this state
    private static int evaluate(GameBoard gameBoard, int depth) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (gameBoard.getValueFromIndex(row, 0) == gameBoard.getValueFromIndex(row, 1) &&
                    gameBoard.getValueFromIndex(row, 1) == gameBoard.getValueFromIndex(row, 2)) {
                if (gameBoard.getValueFromIndex(row, 0) == currentPlayer)
                    return +10;
                else if (gameBoard.getValueFromIndex(row, 0) == opponent)
                    return -10;
            }
        }
        // Checking for Columns for X or O victory.
        for (int column = 0; column < 3; column++) {
            if (gameBoard.getValueFromIndex(0, column) == gameBoard.getValueFromIndex(1, column) &&
                    gameBoard.getValueFromIndex(1, column) == gameBoard.getValueFromIndex(2, column)) {
                if (gameBoard.getValueFromIndex(0, column) == currentPlayer)
                    return +10;

                else if (gameBoard.getValueFromIndex(0, column) == opponent)
                    return -10;
            }
        }
        // Checking for main diagonal for X or O victory.
        if (gameBoard.getValueFromIndex(0, 0) == gameBoard.getValueFromIndex(1, 1) &&
                gameBoard.getValueFromIndex(1, 1) == gameBoard.getValueFromIndex(2, 2)) {
            if (gameBoard.getValueFromIndex(0, 0) == currentPlayer)
                return +10 ;
            else if (gameBoard.getValueFromIndex(0, 0) == opponent)
                return -10 ;
        }
        //check second diagonal
        if (gameBoard.getValueFromIndex(2,0) == gameBoard.getValueFromIndex(1, 1) &&
                gameBoard.getValueFromIndex(1, 1) == gameBoard.getValueFromIndex(0, 2))
        {
            if (gameBoard.getValueFromIndex(2, 0) == currentPlayer)
                return +10 ;
            else if (gameBoard.getValueFromIndex(2, 0) == opponent)
                return -10;
        }

        // if none of the players have won then return 0
        return 0;

    }

    //goes through all possible moves and returns the value of the board
    private static int minimax(GameBoard gameBoard, int depth, Boolean isMax) {

        int score = evaluate(gameBoard, depth);


        //if currentPlayer or opponent has won the game, return his evaluated score
        if (score == 10)
            return score;
        if (score == -10) {
            return score;
        }

        //if now winner and no empty cells, return 0
        if (!gameBoard.isEmptyCells()) {
            return 0;
        }


        //if the currentPlayer has to move
        //currentPlayer is maximizer
        if (isMax) {
            int bestScore = -100000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (!gameBoard.ifCellIsOccupied(i, j)) //false, if cell is not occupied
                    {
                        // Make the move
                        gameBoard.setValueToEmptyCellMinimax(currentPlayer, i, j);

                        // Call minimax recursively and choose
                        // the maximum value
                        bestScore = Math.max(bestScore, minimax(gameBoard,
                                depth + 1, false));

                        // Undo the move
                        gameBoard.setValueToEmptyCellMinimax(' ', i, j);
                    }
                }
            }
            return bestScore + depth;
        } else {
            int bestScore = 100000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //check if cell is empty
                    if (!gameBoard.ifCellIsOccupied(i, j)) { //false, if cell is not occupied
                        //Make the move
                        gameBoard.setValueToEmptyCellMinimax(opponent, i, j);
                        //Call minimax recursively and choose the minimum value
                        bestScore = Math.min(bestScore, minimax(gameBoard, depth + 1, true));

                        // Undo the move
                        gameBoard.setValueToEmptyCellMinimax(' ', i, j);
                    }
                }
            }
            return bestScore - depth;
        }
    }


        public static int[] findBestMove(GameBoard gameBoard, char actualPlayer, char enemy) {
            currentPlayer = actualPlayer;
            opponent = enemy;
            int bestValue = -100000;
            int row = -1;
            int column = -1;
            int[] result = new int[2];
            result[0] = -1;
            result[1] = -1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (!gameBoard.ifCellIsOccupied(i, j)) //false, if cell is not occupied
                    {
                        // Make the move
                        gameBoard.setValueToEmptyCellMinimax(currentPlayer, i, j);

                        // compute evaluation function for this move.
                        int actualValue = minimax(gameBoard, 0, false);

                        // Undo the move
                        gameBoard.setValueToEmptyCellMinimax(' ', i, j);

                        // If the value of the current move is more than the best value, then update the best value
                        if (actualValue > bestValue)
                        {
                            row = i;
                            column = j;
                            bestValue = actualValue;
                        }
                    }
                }
            }
            result[0] = row;
            result[1] = column;
            System.out.println("THe value of the best Move is: " + bestValue + ".");
            return result;
        }









        /*

        //!!!!! Verwende vielleicht diese Funktion in StateOfTheGame
        // winning combinations using the board indexies, returns true if
        public static boolean winning (GameBoard gB,char value){
            if (
                    (gB.getValueFromIndex(0, 0) == value && gB.getValueFromIndex(0, 1) == value && gB.getValueFromIndex(0, 2) == value) ||
                            (gB.getValueFromIndex(1, 0) == value && gB.getValueFromIndex(1, 1) == value && gB.getValueFromIndex(1, 2) == value) ||
                            (gB.getValueFromIndex(2, 0) == value && gB.getValueFromIndex(2, 1) == value && gB.getValueFromIndex(2, 2) == value) ||
                            (gB.getValueFromIndex(0, 0) == value && gB.getValueFromIndex(1, 0) == value && gB.getValueFromIndex(2, 0) == value) ||
                            (gB.getValueFromIndex(0, 1) == value && gB.getValueFromIndex(1, 1) == value && gB.getValueFromIndex(2, 1) == value) ||
                            (gB.getValueFromIndex(0, 2) == value && gB.getValueFromIndex(1, 2) == value && gB.getValueFromIndex(2, 2) == value) ||
                            (gB.getValueFromIndex(0, 0) == value && gB.getValueFromIndex(1, 1) == value && gB.getValueFromIndex(2, 2) == value) ||
                            (gB.getValueFromIndex(2, 0) == value && gB.getValueFromIndex(1, 1) == value && gB.getValueFromIndex(0, 2) == value)
            ) {
                return true;
            } else {
                return false;
            }
        }

        public static int[] minimax1 (GameBoard newBoard, Player player,boolean isMax){
            //determine available spots on the board and save them in an arraylist
            ArrayList<int[]> availableSpots = newBoard.emptyIndexies();

            // checks for the terminal states such as win, lose, and tie
            //and returning a value accordingly
            if (winning(newBoard, player.getValueOfEnemy())) {
                return new int[]{-10, 0};
            } else if (winning(newBoard, player.getValueToMove())) {
                return new int[]{10, 0};
            } else if (availableSpots.size() == 0) {
                return new int[]{0, 0};
            }

            //an array to collect the scores from each of the empty spots to evaluate later -
            // collect each move's index and score in an object called move
            ArrayList<Move> moves = new ArrayList<>();


            //loop through available spots
            for (int i = 0; i < availableSpots.size(); i++) {
                //create an object for each and store the index of that spot
                Move move = new Move();
                move.setIndex(availableSpots.get(i)); //get an index of available spot and save as property of move object

                //set empty spot to the current player
                newBoard.setValueToEmptyCellMinimax(player.getValueToMove(), availableSpots.get(i));

            /*collect the score resulted from calling minimax
                on the opponent of the current player*/
        /*

                int[] result = minimax(newBoard, player.getEnemy(), !isMax);
                move.setScore(result[0]);


                //reset the spot to empty
                newBoard.setValueToEmptyCellMinimax(' ', availableSpots.get(i));

                //push the object to the array
                moves.add(move);
            }

            //if it is the computer's turn, loop over the moves and choose the move with the highest score
            int bestMove = 0;

            if (isMax) {
                int bestScore = -10000;
                for (int i = 0; i < moves.size(); i++) {
                    if (moves.get(i).getScore() > bestScore) {
                        bestScore = moves.get(i).getScore();
                        bestMove = i;
                    }
                }
            } else {
                // else loop over the moves and choose the move with the lowest score
                int bestScore = 10000;

                for (int i = 0; i < moves.size(); i++) {
                    if (moves.get(i).getScore() < bestScore) {
                        bestScore = moves.get(i).getScore();
                        bestMove = i;
                    }
                }
            }

            return moves.get(bestMove).getIndex();


    */

        /*
        var moves = [];
        for (var i = 0; i < array.length; i++) {
            var move = {};
            move.index = reboard[array[i]];
            reboard[array[i]] = player;

            if (player == aiPlayer) {
                var g = minimax(reboard, huPlayer);
                move.score = g.score;
            } else {
                var g = minimax(reboard, aiPlayer);
                move.score = g.score;
            }
            reboard[array[i]] = move.index;
            moves.push(move);
        }

        */

        /*
        }
        */



}
