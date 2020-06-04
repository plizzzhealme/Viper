package io.github.plizzzhealme.model.combat;

import io.github.plizzzhealme.model.character.Boxer;
import io.github.plizzzhealme.model.character.Hero;

public class Combat {
    private final Hero player;
    private final Boxer enemy;
    private boolean specialAttackUsed;

    public Combat(Hero player, Boxer enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public Turn makeAttackTurn() {
        Turn turn;
        int damageDone;
        int damageTaken;

        damageDone = player.attack();
        damageTaken = enemy.takeDamage(damageDone);
        turn = new Turn(Turn.ATTACK, damageTaken);
        makeBotTurn(turn);
        return turn;
    }

    public Turn makeSpecialAttackTurn() {
        Turn turn;
        int damageDone;
        int damageTaken;

        damageDone = player.makeSpecialAttack();
        specialAttackUsed = true;
        damageTaken = enemy.takeDamage(damageDone);
        turn = new Turn(Turn.SPECIAL_ATTACK, damageTaken);
        makeBotTurn(turn);
        return turn;
    }

    public Turn makeRecoveryTurn() {
        Turn turn;

        player.recoverStamina();
        turn = new Turn(Turn.RECOVER, player.getStaminaRegen());
        makeBotTurn(turn);
        return turn;
    }

    private void makeBotTurn(Turn turn) {
        if (enemy.isAttackAvailable()) {
            int damageDone;
            int damageTaken;

            damageDone = enemy.attack();
            damageTaken = player.takeDamage(damageDone);
            turn.addEnemyTurn(Turn.ATTACK, damageTaken);
        } else {
            enemy.recoverStamina();
            turn.addEnemyTurn(Turn.RECOVER, enemy.getStaminaRegen());
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
                        player.getCurrentHealth(),
                        player.getMaxHealth(),
                        enemy.getName(),
                        enemy.getCurrentHealth(),
                        enemy.getMaxHealth()));
    }

    public Hero getPlayer() {
        return player;
    }

    public Boxer getEnemy() {
        return enemy;
    }
}