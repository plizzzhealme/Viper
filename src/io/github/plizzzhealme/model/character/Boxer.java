package io.github.plizzzhealme.model.character;

import javax.swing.*;
import java.util.Random;

public class Boxer {
    static final Random RANDOM = new Random();
    static final int MAX_DODGE = 75;
    static final int MAX_DEFENCE = 75;

    final String name;
    final ImageIcon icon;

    int strength;
    int agility;

    transient int damage;
    transient int defence;
    transient int currentHealth;
    transient int maxHealth;
    transient int attackCost;
    transient int dodge;
    transient int currentStamina;
    transient int maxStamina;
    transient int staminaRegen;

    public Boxer(String name, ImageIcon icon, int strength, int agility) {
        this.name = name;
        this.icon = icon;
        this.strength = strength;
        this.agility = agility;

        dodge = (int) Math.min((1.6 * (agility + 2.75) + 0.5), MAX_DODGE);
        maxStamina = (int) (28 + 0.4 * agility + 0.5);
        currentStamina = maxStamina;
        staminaRegen = (int) (2.9 + 0.37 * agility + 0.5);
        damage = (int) (10.25 + strength * 1.4 + 0.5);
        defence = Math.min(3 + strength, MAX_DEFENCE);
        maxHealth = 110 + 3 * strength;
        currentHealth = maxHealth;
        attackCost = Math.min((int) (3.55 + 0.45 * strength + 0.5), maxStamina);
    }

    public Boxer(Boxer boxer) {
        this(boxer.name, boxer.icon, boxer.strength, boxer.agility);
    }

    public int attack() {
        if (isAttackAvailable()) {
            currentStamina -= attackCost;
            return damage;
        } else {
            return 0;
        }
    }

    public int takeDamage(int damageDone) {
        boolean isDodged = (RANDOM.nextInt(100) + 1 <= dodge);
        int damageTaken = isDodged ? 0 : (int) (damageDone * (1 - defence / 100.));
        currentHealth -= damageTaken;

        if (currentHealth < 0) {
            currentHealth = 0;
        }
        return damageTaken;
    }

    public void recoverStamina() {
        currentStamina += staminaRegen;
        currentStamina = Math.min(currentStamina, maxStamina);
    }

    public void recoverAfterCombat() {
        currentHealth = maxHealth;
        currentStamina = maxStamina;
    }

    public boolean isDead() {
        return currentHealth == 0;
    }

    public boolean isAttackAvailable() {
        return currentStamina >= attackCost;
    }

    @Override
    public String toString() {
        return String.format("%s%n", name)
                + String.format("Strength: %d%n", strength)
                + String.format("Agility: %d%n", agility)
                + String.format("Health: %d/%d%n", currentHealth, maxHealth)
                + String.format("Stamina: %d/%d%n", currentStamina, maxStamina)
                + String.format("Damage: %d%n", damage)
                + String.format("Attack cost: %d%n", attackCost)
                + String.format("Defence: %d%%%n", defence)
                + String.format("Dodge: %d%%", dodge);
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStaminaRegen() {
        return staminaRegen;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefence() {
        return defence;
    }

    public int getDodge() {
        return dodge;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}