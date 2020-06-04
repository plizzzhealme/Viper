package io.github.plizzzhealme.model.character;

import javax.swing.*;

public class Hero extends Boxer {
    private static final int SPECIAL_ATTACK_COST = 10;

    public Hero(String name, ImageIcon icon, int strength, int agility) {
        super(name, icon, strength, agility);
    }

    public Hero(Boxer boxer) {
        super(boxer);
    }

    public int makeSpecialAttack() {
        if (isSpecialAttackAvailable()) {
            currentStamina -= SPECIAL_ATTACK_COST;
            return 3 * Math.max(strength, agility);
        } else {
            return 0;
        }
    }

    public boolean isSpecialAttackAvailable() {
        return currentStamina >= SPECIAL_ATTACK_COST;
    }

    public void increaseStrength(int str) {
        strength += str;

        damage = (int) (10.25 + strength * 1.4 + 0.5);
        defence = Math.min((int) (2.5 + strength + 0.5), MAX_DEFENCE);
        maxHealth = 110 + 3 * strength;
        currentHealth = maxHealth;
        attackCost = Math.min((int) (3.55 + 0.45 * strength + 0.5), maxStamina);
    }

    public void increaseAgility(int agi) {
        agility += agi;

        dodge = Math.min((int) (1.6 * (agility + 2.75) + 0.5), MAX_DODGE);
        maxStamina = (int) (28 + 0.4 * agility + 0.5);
        currentStamina = maxStamina;
        staminaRegen = (int) (2.9 + 0.37 * agility + 0.5);
        attackCost = Math.min((int) (3.55 + 0.45 * strength + 0.5), maxStamina);
    }
}