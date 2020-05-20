package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final String IN_COMBAT_EXCEPTION = "You are in combat";
    private static final String NOT_IN_COMBAT_EXCEPTION = "You are not in combat";
    private static final String REST_REQUIRED_EXCEPTION = "Rest required";
    private static final String GAME_ENDED_EXCEPTION = "Game is ended";
    private static final Random RANDOM = new Random();

    private final String nickname;
    private final Hero player;
    private final List<Boxer> enemies;
    private final int weekLimit;
    private final int lossLimit;
    private int currentWeek;
    private int lossCount;
    private int winCount;
    private boolean restRequired;
    private Combat combat;
    private String gameResult;
    private boolean inCombat;

    public Game(String nickname, Hero player, List<Boxer> enemies, int weekLimit, int lossLimit) {
        this.nickname = nickname;
        this.player = new Hero(player);
        this.enemies = new ArrayList<>(enemies.size());
        this.weekLimit = weekLimit;
        this.lossLimit = lossLimit;

        for (Boxer enemy : enemies) {
            this.enemies.add(new Boxer(enemy));
        }
    }

    /**
     * Starts new combat
     */
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
    }

    /**
     * Finishes current combat
     */
    public void finishCombat() {
        if (!isInCombat()) {
            throw new IllegalStateException(NOT_IN_COMBAT_EXCEPTION);
        }

        restRequired = true;
        inCombat = false;

        if (player.isDead() || !combat.isCombatEnded()) {
            lossCount++;
        } else {
            winCount++;
        }
    }

    /**
     * Recover after combat
     * @return rest time in weeks
     */
    public int rest() {
        if (isInCombat()) {
            throw new IllegalStateException(IN_COMBAT_EXCEPTION);
        }

        if (isGameEnded()) {
            throw new IllegalStateException(GAME_ENDED_EXCEPTION);
        }

        if (isRestRequired()) {
            int restTime;
            int hpPercents = 100 * player.getCurrentHp() / player.getMaxHp();

            if (hpPercents == 0) {
                restTime = 7;
            } else if (hpPercents < 25) {
                restTime = 6;
            } else if (hpPercents < 50) {
                restTime = 5;
            } else if (hpPercents < 80) {
                restTime = 4;
            } else {
                restTime = 3;
            }
            currentWeek += restTime;
            restRequired = false;
            player.recoverAfterCombat();
            return restTime;
        } else {
            return 0;
        }
    }

    /**
     * Trains strength
     * @return gained strength
     */
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

    /**
     * Trains agility
     * @return gained agility
     */
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

        int bonus = player.getAgility() * (RANDOM.nextInt(25) + 1) / 100 + 1;
        player.increaseAgility(bonus);
        currentWeek++;

        return bonus;
    }

    /**
     * Finishes the game
     */
    public void finishGame() {
        int playerScores;

        if (winCount == enemies.size()) {
            playerScores = 200 + weekLimit - currentWeek + winCount - lossCount;
        } else if (currentWeek >= weekLimit) {
            playerScores = 100 + winCount - lossCount;
        } else if (lossCount > lossLimit) {
            playerScores = 10 + currentWeek + winCount - lossCount;
        } else {
            playerScores = 0;
        }
        gameResult = String.format("%s %s %d%n", nickname, player.getName(), playerScores);
    }

    public boolean isGameEnded() {
        return winCount == enemies.size()
               || lossCount > lossLimit
               || currentWeek > weekLimit;
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
        return String.format("[Week: %d/%d Wins: %d/%d Losses: %d/%d]",
                currentWeek,
                weekLimit,
                winCount,
                enemies.size(),
                lossCount,
                lossLimit);
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

    public static String getInCombatException() {
        return IN_COMBAT_EXCEPTION;
    }

    public static String getNotInCombatException() {
        return NOT_IN_COMBAT_EXCEPTION;
    }

    public static String getRestRequiredException() {
        return REST_REQUIRED_EXCEPTION;
    }

    public static String getGameEndedException() {
        return GAME_ENDED_EXCEPTION;
    }

    public static Random getRANDOM() {
        return RANDOM;
    }

    public String getNickname() {
        return nickname;
    }

    public List<Boxer> getEnemies() {
        return enemies;
    }

    public int getWeekLimit() {
        return weekLimit;
    }

    public int getLossLimit() {
        return lossLimit;
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