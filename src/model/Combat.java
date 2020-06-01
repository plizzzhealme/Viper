package model;

public class Combat {
    /**
     * User
     */
    private final Hero player;

    /**
     * Bot
     */
    private final Boxer enemy;

    /**
     * Special attack may be used once per combat
     */
    private boolean specialAttackUsed;

    private boolean playersTurn;

    public Combat(Hero player, Boxer enemy) {
        this.player = player;
        this.enemy = enemy;
        playersTurn = true;
    }

    public int makeAttackTurn() {
        int damageDone;
        int damageTaken;

        damageDone = player.attack();
        damageTaken = enemy.takeDamage(damageDone);
        playersTurn = false;
        return damageTaken;
    }

    public int makeSpecialAttackTurn() {
        int damageDone;
        int damageTaken;

        specialAttackUsed = true;
        playersTurn = false;
        damageDone = player.makeSpecialAttack();
        damageTaken = enemy.takeDamage(damageDone);
        return damageTaken;
    }

    public int makeRecoveryTurn() {
        return -player.recoverStamina();
    }

    public int makeBotTurn() {
        if (enemy.isAttackAvailable()) {
            int damageDone;
            int damageTaken;

            damageDone = enemy.attack();
            damageTaken = player.takeDamage(damageDone);
            return damageTaken;
        } else {
            return -enemy.recoverStamina();
        }
    }

    public boolean isSpecialAttackUsed() {
        return specialAttackUsed;
    }

    public boolean isCombatEnded() {
        return player.isDead() || enemy.isDead();
    }

    @Override
    public String toString() {
        return (
                String.format("%s %d/%d VS %s %d/%d",
                        player.getName(),
                        player.getCurrentHp(),
                        player.getMaxHp(),
                        enemy.getName(),
                        enemy.getCurrentHp(),
                        enemy.getMaxHp()));
    }

    public Hero getPlayer() {
        return player;
    }

    public Boxer getEnemy() {
        return enemy;
    }
}