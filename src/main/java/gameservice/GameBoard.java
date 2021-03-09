package gameservice;

//class for the game table with getters and setters


import java.util.ArrayList;


public final class GameBoard {
    private char[][] board;
    private int counterOfX;
    private int counterOfO;
    private int numberOfMoves;

    public GameBoard() {
        this.board = new char[3][3];
        this.counterOfO = 0;
        this.counterOfX = 0;
        this.numberOfMoves = 0;
        int counter = 0;
        for (char[] row : board) {
            for (int j = 0; j < board.length; j++) {
                row[j] = ' ';
            }
        }
        //display the table
        Console.displayTable(board);
    }

    //getters
    public char[][] getBoard() {
        return board;
    }

    public char getValueFromIndex (int index1, int index2) {
        return board[index1][index2];
    }

    public int getCounterOfO() {
        return counterOfO;
    }

    public int getCounterOfX() {
        return counterOfX;
    }

    public void setCounterOfO(int counterOfO) {
        this.counterOfO = counterOfO;
    }

    public void setCounterOfX(int counterOfX) {
        this.counterOfX = counterOfX;
    }

    //sets a move to an empty cell
    public void setValueToEmptyCell (char player, int[] coordinates) {
        this.board[coordinates[0]][coordinates[1]] = player;
        if (player == 'X') {
            counterOfX++;
        } else {
            counterOfO++;
        }
        numberOfMoves++;
        Console.displayTable(this.getBoard());
    }

    //sets a move to an empty cell
    public void setValueToEmptyCellMinimax (char player, int firstIndex, int secondIndex) {
        this.board[firstIndex][secondIndex] = player;
        if (player == 'X') {
            counterOfX++;
        } else {
            counterOfO++;
        }
        numberOfMoves++;
        //Console.displayTable(this.getBoard());
    }



    //checks whether the cell is already occupied
    public boolean ifCellIsOccupied (int firstIndex, int secondIndex) {
        //return map[firstIndex][secondIndex] != ' ';
        return getValueFromIndex(firstIndex, secondIndex) != ' ';
    }

    // returns list of the indexes of empty cells on the board
    public ArrayList<int[]> emptyIndexies() {
       ArrayList<int[]> listOfEmptyIndexies = new ArrayList<>();
       for (int row = 0; row < this.board.length; row++) {
           for (int column = 0; column < this.board.length; column++) {
               if (Character.getNumericValue(this.getValueFromIndex(row, column)) == -1) {
                   int[] coordinates = {row, column};
                   listOfEmptyIndexies.add(coordinates);
               }
           }
       }
        return listOfEmptyIndexies;
    }


    //checks if there is any free cell. Returns false if there are no moves left to play
    public boolean isEmptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Character.getNumericValue(board[i][j]) == -1) {
                    return true;
                }
            }
        }
        return false;
    }




    //check which cell is empty in a row
    public int[] findEmptyCellInARow(int row) {
        int[] coordinates = new int[2];
        for (int column = 0; column < 3; column++) {
            if (Character.getNumericValue(this.getValueFromIndex(row, column)) == -1) {
                coordinates[0] = row;
                coordinates[1] = column;
                return coordinates;
            }
        }
        return new int[] {5, 5}; //maybe change it
    }

    //check which cell is empty in a column
    public int[] findEmptyCellInAColumn(int column) {
        int[] coordinates = new int[2];
        for (int row = 0; row < 3; row++) {
            if (Character.getNumericValue(this.getValueFromIndex(row, column)) == -1) {
                coordinates[0] = row;
                coordinates[1] = column;
                return coordinates;
            }
        }
        return new int[] {5, 5}; //maybe change it
    }

    //check which cell is empty in a column
    public int[] findEmptyCellInADiagonale(String diagonale) {
        int[] coordinates = new int[2];
        int i;
        int j;
        if (diagonale.equals("main")) {
            //check the main diagonale of matrix
            i = 0;
            j = 0;
            int mainDiagonale = 0;
            while (i<=2) { //go from i=0, j=0 to i=2,j=2
                if (Character.getNumericValue(this.getValueFromIndex(i, j)) == -1) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
                i++;
                j++;
            }
            return new int[] {5, 5};
        } else {
            //check the second diagonale of matrix
            int secondDiagonale = 0;
            i = 2;
            j = 0;
            while (i>=0) { //go from i=2, j=0 to i=0,j=2
                if (Character.getNumericValue(this.getValueFromIndex(i, j)) == -1) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
                i--;
                j++;
            }
        }
        return new int[] {5, 5}; //maybe change it
    }

}
