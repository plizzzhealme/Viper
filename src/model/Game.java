package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static final int TIME_LIMIT = 40;
    public static final int LOSS_LIMIT = 2;
    public static final List<Boxer> enemies = new ArrayList<>();
    public static final List<Hero> heroes = new ArrayList<>();

    private static final String IN_COMBAT_EXCEPTION = "You are in combat";
    private static final String NOT_IN_COMBAT_EXCEPTION = "You are not in combat";
    private static final String REST_REQUIRED_EXCEPTION = "Rest is required";
    private static final String REST_NOT_REQUIRED_EXCEPTION = "Rest is not required";
    private static final String GAME_ENDED_EXCEPTION = "Game is ended";
    private static final Random RANDOM = new Random();

    static {
        enemies.add(new Boxer("Jagger", new ImageIcon("resources/enemies/enemy-0.gif"), 7, 6));
        enemies.add(new Boxer("Nakamura", new ImageIcon("resources/enemies/enemy-1.gif"), 10, 9));
        enemies.add(new Boxer("Garcia", new ImageIcon("resources/enemies/enemy-2.gif"), 9, 16));
        enemies.add(new Boxer("King", new ImageIcon("resources/enemies/enemy-3.gif"), 17, 14));

        heroes.add(new Hero("Bulldog", new ImageIcon("resources/heroes/hero-0.gif"), 8, 2));
        heroes.add(new Hero("Connor", new ImageIcon("resources/heroes/hero-1.gif"), 3, 7));
    }

    private Hero player;
    private int currentWeek;
    private int lossCount;
    private int winCount;
    private boolean restRequired;
    private Combat combat;
    private String gameResult;
    private boolean inCombat;

    public Game() {
        currentWeek = 1;
    }

    public void selectHero(int i) {
        player = new Hero(heroes.get(i));
    }

    public void startNewCombat() {
        if (isGameEnded()) {
            throw new IllegalStateException(GAME_ENDED_EXCEPTION);
        }

        if (isInCombat()) {
            throw new IllegalStateException(IN_COMBAT_EXCEPTION);
        }

        if (isRestRequired()) {
            throw new IllegalStateException(REST_REQUIRED_EXCEPTION);
        }

        combat = new Combat(player, new Boxer(enemies.get(winCount)));
        inCombat = true;
        restRequired = true;
    }

    public void finishCombat() {
        if (!isInCombat()) {
            throw new IllegalStateException(NOT_IN_COMBAT_EXCEPTION);
        }

        inCombat = false;

        if (player.isDead() || !combat.isCombatEnded()) {
            lossCount++;
        } else {
            winCount++;
        }
    }

    public int rest() {
        if (isInCombat()) {
            throw new IllegalStateException(IN_COMBAT_EXCEPTION);
        }

        if (isGameEnded()) {
            throw new IllegalStateException(GAME_ENDED_EXCEPTION);
        }

        if (!isRestRequired()) {
            throw new IllegalStateException(REST_NOT_REQUIRED_EXCEPTION);
        }

        int restTime;
        int hpPercents = 100 * player.getCurrentHp() / player.getMaxHp();

        if (hpPercents == 0) {
            restTime = 7;
        } else if (hpPercents < 25) {
            restTime = 6;
        } else if (hpPercents < 50) {
            restTime = 5;
        } else if (hpPercents < 75) {
            restTime = 4;
        } else {
            restTime = 3;
        }

        currentWeek += restTime;
        restRequired = false;
        player.recoverAfterCombat();
        return restTime;
    }

    public int workout() {
        if (isGameEnded()) {
            throw new IllegalStateException(GAME_ENDED_EXCEPTION);
        }

        if (isInCombat()) {
            throw new IllegalStateException(IN_COMBAT_EXCEPTION);
        }

        if (isRestRequired()) {
            throw new IllegalStateException(REST_REQUIRED_EXCEPTION);
        }

        int bonus = (int) (player.getStrength() * ((RANDOM.nextInt(25) + 1.) / 100) + 1.5);
        player.increaseStrength(bonus);
        currentWeek++;

        return bonus;
    }

    public int cardio() {
        if (isGameEnded()) {
            throw new IllegalStateException(GAME_ENDED_EXCEPTION);
        }

        if (isInCombat()) {
            throw new IllegalStateException(IN_COMBAT_EXCEPTION);
        }

        if (isRestRequired()) {
            throw new IllegalStateException(REST_REQUIRED_EXCEPTION);
        }

        int bonus = player.getAgility() * (RANDOM.nextInt(21) + 5) / 100 + 1;
        player.increaseAgility(bonus);
        currentWeek++;

        return bonus;
    }

    public void finishGame() {
        int playerScores;

        if (winCount == enemies.size()) {
            playerScores = 200 + TIME_LIMIT - currentWeek + winCount - lossCount;
        } else if (currentWeek >= TIME_LIMIT) {
            playerScores = 100 + winCount - lossCount;
        } else if (lossCount > LOSS_LIMIT) {
            playerScores = 10 + currentWeek + winCount - lossCount;
        } else {
            playerScores = 0;
        }
        gameResult = String.format("%s %s %d%n", "nickname", player.getName(), playerScores);
    }

    public boolean isGameEnded() {
        return winCount == enemies.size()
                || lossCount > LOSS_LIMIT
                || currentWeek > TIME_LIMIT;
    }

    public boolean isInCombat() {
        return inCombat;
    }

    public Boxer getCurrentEnemy() {
        return (winCount < enemies.size()) ? enemies.get(winCount) : null;
    }

    public boolean isRestRequired() {
        return restRequired;
    }

    @Override
    public String toString() {
        return "Game{" +
                "nickname='" + "nickname" + '\'' +
                ", player=" + player.getName() +
                // ", enemies=" + enemies +
                ", weekLimit=" + TIME_LIMIT +
                ", lossLimit=" + LOSS_LIMIT +
                ", currentWeek=" + currentWeek +
                ", lossCount=" + lossCount +
                ", winCount=" + winCount +
                ", restRequired=" + restRequired +
                ", combat=" + combat +
                ", gameResult='" + gameResult + '\'' +
                ", inCombat=" + inCombat +
                '}';
    }

    public Hero getPlayer() {
        return player;
    }

    public Combat getCombat() {
        return combat;
    }

    public String getGameResult() {
        return gameResult;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public int getLossCount() {
        return lossCount;
    }

    public int getWinCount() {
        return winCount;
    }

}