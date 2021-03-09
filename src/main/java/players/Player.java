package players;


import gameservice.GameBoard;

public abstract class Player {
    private final String name;
    private final char valueToMove;
    private final char valueOfEnemy;
    private Player enemy;

    public Player(String name, char valueToMove, char valueOfEnemy) {
        this.name = name;
        this.valueToMove = valueToMove;
        this.valueOfEnemy = valueOfEnemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public Player getEnemy() {
        return enemy;
    }

    //returns the name of the player: easy or user
    public String getName() {
        return name;
    }

    //returns X or O
    public char getValueToMove() {
        return valueToMove;
    }
    //returns X or O
    public char getValueOfEnemy() {
        return valueOfEnemy;
    }

    //take a move (AI and user must implement their own way to move)
    public abstract int[] makesMove(GameBoard gameBoard) throws InterruptedException;
}
