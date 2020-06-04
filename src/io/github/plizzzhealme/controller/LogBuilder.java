package io.github.plizzzhealme.controller;

import io.github.plizzzhealme.model.Game;
import io.github.plizzzhealme.model.character.Boxer;
import io.github.plizzzhealme.model.character.Hero;
import io.github.plizzzhealme.model.combat.Turn;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogBuilder {
    static String buildToolTip(Boxer boxer) {
        String pattern = "<html>%s<br/>" +
                "Strength: %d<br/>" +
                "Agility: %d<br/>" +
                "Health: %d<br/>" +
                "Stamina: %d<br/>" +
                "Defence: %d%%<br/>" +
                "Dodge: %d%%<br/></html>";
        return String.format(pattern,
                boxer.getName(),
                boxer.getStrength(),
                boxer.getAgility(),
                boxer.getMaxHealth(),
                boxer.getMaxStamina(),
                boxer.getDefence(),
                boxer.getDodge());
    }

    static String buildWorkoutLog(int bonusStrength) {
        return String.format("<html>You trained for a week ang gained <b>%d</b> bonus strength</html>", bonusStrength);
    }

    static String buildCardioLog(int bonusAgility) {
        return String.format("<html>You trained for a week ang gained <b>%d</b> bonus agility</html>", bonusAgility);
    }

    static String buildRestLog(int restTime) {
        return String.format("<html>You rested for <b>%d</b> weeks</html>", restTime);
    }

    static String buildTurnLog(Turn turn, Hero player, Boxer enemy) {
        String log = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String playerName = player.getName();
        String enemyName = enemy.getName();

        int turnType;

        turnType = turn.getPlayerTurnType();

        switch (turnType) {
            case Turn.ATTACK:
            case Turn.SPECIAL_ATTACK:
                if (turn.getPlayerValue() == 0) {
                    log += String.format("[%s] %s dodged %s's attack%n",
                            LocalTime.now().format(formatter),
                            playerName,
                            enemyName);
                } else {
                    log += String.format("[%s] %s hit %s for %d damage%n",
                            LocalTime.now().format(formatter),
                            playerName,
                            enemyName,
                            turn.getPlayerValue());
                }
                break;
            case Turn.RECOVER:
                log += String.format("[%s] %s recovered %d stamina%n",
                        LocalTime.now().format(formatter),
                        playerName,
                        turn.getPlayerValue());
                break;
        }

        turnType = turn.getEnemyTurnType();

        switch (turnType) {
            case Turn.ATTACK:
                if (turn.getEnemyValue() == 0) {
                    log += String.format("[%s] %s dodged %s's attack%n",
                            LocalTime.now().format(formatter),
                            enemyName,
                            playerName);
                } else {
                    log += String.format("[%s] %s hit %s for %d damage%n",
                            LocalTime.now().format(formatter),
                            enemyName,
                            playerName,
                            turn.getEnemyValue());
                }
                break;
            case Turn.RECOVER:
                log += String.format("[%s] %s recovered %d stamina%n",
                        LocalTime.now().format(formatter),
                        enemyName,
                        turn.getEnemyValue());
                break;
        }

        return log;
    }

    static String buildGameLog(Game game) {
        return String.format("Week: %d/%d Wins: %d/%d Losses: %d/%d",
                game.getCurrentWeek(),
                Game.TIME_LIMIT,
                game.getWinCount(),
                Game.enemies.size(),
                game.getLossCount(),
                Game.LOSS_LIMIT);
    }
}