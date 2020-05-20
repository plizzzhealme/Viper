package model;

import java.util.Random;

public class Boxer {
    static final Random RANDOM = new Random();
    static final int MAX_DODGE = 75;
    static final int MAX_DEFENCE = 75;
    final String name;

    int strength;
    int agility;
    int damage;
    int defence;
    int currentHp;
    int maxHp;
    int attackCost;
    int dodge;
    int currentStamina;
    int maxStamina;
    int staminaRegen;

    public Boxer(String name, int strength, int agility) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;

        dodge = (int) Math.min((1.6 * (agility + 2.75) + 0.5), MAX_DODGE);
        maxStamina = (int) (28 + 0.4 * agility + 0.5);
        currentStamina = maxStamina;
        staminaRegen = (int) (2.9 + 0.37 * agility + 0.5);
        damage = (int) (10.25 + strength * 1.4 + 0.5);
        defence = Math.min(3 + strength, MAX_DEFENCE);
        maxHp = 110 + 3 * strength;
        currentHp = maxHp;
        attackCost = Math.min((int) (3.55 + 0.45 * strength + 0.5), maxStamina);
    }

    @SuppressWarnings("CopyConstructorMissesField")
    public Boxer(Boxer boxer) {
        this(boxer.getName(), boxer.getStrength(), boxer.getAgility());
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
        currentHp -= damageTaken;

        if (currentHp < 0) {
            currentHp = 0;
        }
        return damageTaken;
    }

    public int recoverStamina() {
        int temp = currentStamina;
        currentStamina += staminaRegen;
        currentStamina = Math.min(currentStamina, maxStamina);
        return currentStamina - temp;
    }

    public void recoverAfterCombat() {
        currentHp = maxHp;
        currentStamina = maxStamina;
    }

    public boolean isDead() {
        return currentHp == 0;
    }

    public boolean isAttackAvailable() {
        return currentStamina >= attackCost;
    }

    @Override
    public String toString() {
        return String.format("%s%n", name)
               + String.format("Strength: %d%n", strength)
               + String.format("Agility: %d%n", agility)
               + String.format("Health: %d/%d%n", currentHp, maxHp)
               + String.format("Stamina: %d/%d%n", currentStamina, maxStamina)
               + String.format("Damage: %d%n", damage)
               + String.format("Attack cost: %d%n", attackCost)
               + String.format("Defence: %d%%%n", defence)
               + String.format("Dodge: %d%%", dodge);
    }

    public String getName() {
        return name;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
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
}