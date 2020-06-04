package io.github.plizzzhealme.model.combat;

public class Turn {
    public static final int ATTACK = 1;
    public static final int SPECIAL_ATTACK = 2;
    public static final int RECOVER = 3;

    private final int playerTurnType;
    private final int playerValue;
    private int enemyTurnType;
    private int enemyValue;

    public Turn(int type, int value) {
        playerTurnType = type;
        playerValue = value;
    }

    public void addEnemyTurn(int type, int value) {
        enemyTurnType = type;
        enemyValue = value;
    }

    public int getPlayerTurnType() {
        return playerTurnType;
    }

    public int getEnemyTurnType() {
        return enemyTurnType;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public int getEnemyValue() {
        return enemyValue;
    }
}