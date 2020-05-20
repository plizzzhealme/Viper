package controller;

import model.Boxer;

import java.time.LocalTime;

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
        if (damage < 0) {
            return "[" + t + "] " + String.format("%s recovered %d stamina", attackerName, -damage);
        } else if (damage == 0) {
            return "[" + t + "] " + String.format("%s dodged %s's attack", defenderName, attackerName);
        } else {
            return "[" + t + "] " + String.format("%s hit %s for %d damage", attackerName, defenderName, damage);
        }
    }
}
