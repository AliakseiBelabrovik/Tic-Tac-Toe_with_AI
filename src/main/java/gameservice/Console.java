package gameservice;

import players.Player;

import java.util.Scanner;

//a separate class only for interaction with the console IO
public final class Console {
private static final Scanner SCANNER = new Scanner(System.in);

    //reads the input and returns it to create an object
    public static char[][] initializationOfTable () {
        char[][] initialTable = new char[3][3];
        //SCANNER.nextLine().replace(" ","").getChars(0,9,userInput,0);
        return initialTable;
    }

    //ask the user for the next command, get called in stateOfTheGame class - startTheGame method
    public static String[] askForACommand() {
        String[] input;
        while (true) {
            System.out.print("Input command: > ");
            input = SCANNER.nextLine().split(" ");
            if (input[0].equals("exit")) {
                return input;

            } else if (input.length < 3) {
                System.out.println("Bad parameters!");
            } else if (input[0].equals("start") &&
                    (input[1].equals("easy") || input[1].equals("user") || input[1].equals("medium") ||
                            input[1].equals("hard")) &&
                    (input[2].equals("user") || input[2].equals("easy") || input[2].equals("medium") ||
                            input[2].equals("hard"))) {
                return input;

            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    //displays actual status of the table
    public static void displayTable (char[][] array) {
        System.out.println("_________");
        for (char[] row : array) {
            System.out.print("| ");
            for (int j = 0; j < array.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("_________");

    }

    public static void displayWhoHasWon (char player) {
        System.out.println(player + " wins");
    }
    public static void displayIfDraw () {
        System.out.println("Draw");
    }
    public static void displayIfNotFinished () {
        System.out.println("Game not finished");
    }
    public static void displayThatAIMakesMove (Player player) {
        System.out.println("Making move level \"" + player.getName() + "\"");
    }

    //aks the user for the coordinates to move on
    public static int[] askForCoordinatesToMove(GameBoard gameBoard) {
        int[] inputCoordinates = new int[2];
        int firstIndex, secondIndex;
        while (true) {
            System.out.print("Enter the coordinates: > ");
            String input = SCANNER.nextLine().replace(" ","");

            char second = input.charAt(1); //first coordinate
            char first = input.charAt(0); //second coordinate

            firstIndex = Character.getNumericValue(first)-1; //if input is 1 - index is 0, because starts at 0
            secondIndex = Character.getNumericValue(second)-1; //if input is 2, indes is 1 and so on

            if ((first < 48 || first > 57) || (second < 48 || second > 57)){
                System.out.println("You should enter numbers!");
            } else if ((first < 49 || first > 51) || (second < 49 || second > 51)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (gameBoard.ifCellIsOccupied(firstIndex, secondIndex)){ //checks if a cell is occupied
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                inputCoordinates[0] = firstIndex;
                inputCoordinates[1] = secondIndex;
                return inputCoordinates; //creates an array for coordinates to enter to the map
            }
        }

    }
}
