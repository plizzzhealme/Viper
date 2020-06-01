package controller;

import model.Boxer;
import model.Game;

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
                boxer.getMaxHp(),
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

    static String buildTurnLog(int damage, String attackerName, String defenderName) {
        LocalTime t = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        if (damage < 0) {
            return String.format("[%s] %s recovered %d stamina",
                    LocalTime.now().format(formatter),
                    attackerName,
                    -damage);
        } else if (damage == 0) {
            return String.format("[%s] %s dodged %s's attack",
                    LocalTime.now().format(formatter),
                    defenderName,
                    attackerName);
        } else {
            return String.format("[%s] %s hit %s for %d damage",
                    LocalTime.now().format(formatter),
                    attackerName,
                    defenderName,
                    damage);
        }
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