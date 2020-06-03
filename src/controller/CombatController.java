package controller;

import model.Boxer;
import model.Combat;
import model.Game;
import model.Hero;
import view.PnlCards;

import java.awt.*;

public class CombatController {
    private final PnlCards pnlCards;
    private final CardLayout cardLayout;
    private Game game;
    private Combat combat;
    private Hero player;
    private Boxer enemy;

    public CombatController(PnlCards pnlCards) {
        this.pnlCards = pnlCards;
        cardLayout = pnlCards.cardLayout;

        pnlCards.addCombatPanelListeners(e -> attack(),
                e -> specialAttack(),
                e -> recover(),
                e -> exit());
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void runNewCombat() {
        combat = game.getCombat();
        player = combat.getPlayer();
        enemy = combat.getEnemy();

        // set initial panel view
        pnlCards.updateCombatView(
                player.getIcon(),
                LogBuilder.buildToolTip(player),
                player.getMaxHp(),
                player.getMaxStamina(),
                enemy.getIcon(),
                LogBuilder.buildToolTip(enemy),
                enemy.getMaxHp(),
                enemy.getMaxStamina()
        );
    }

    private void attack() {
        printCombatLog(combat.makeAttackTurn());
    }

    private void specialAttack() {
        printCombatLog(combat.makeSpecialAttackTurn());
    }

    private void recover() {
        printCombatLog(combat.makeRecoveryTurn());
    }

    private void exit() {
        game.finishCombat();
        cardLayout.show(pnlCards, PnlCards.GAME_PANEL);
    }

    private void printCombatLog(int result) {
        updateView(LogBuilder.buildTurnLog(result,
                game.getCombat().getPlayer().getName(),
                game.getCombat().getEnemy().getName()));

        if (!game.getCombat().isCombatEnded()) {
            result = game.getCombat().makeBotTurn();
            updateView(LogBuilder.buildTurnLog(result,
                    game.getCombat().getEnemy().getName(),
                    game.getCombat().getPlayer().getName()));
        }
    }

    private void updateView(String log) {
        // update combat log and progress bars
        pnlCards.updateCombatView(log,
                player.getCurrentHp(),
                player.getCurrentStamina(),
                enemy.getCurrentHp(),
                enemy.getCurrentStamina());

        if (combat.isCombatEnded()) {
            pnlCards.updateCombatButtonsView(false, false, false, true);
        } else {
            pnlCards.updateCombatButtonsView(player.isAttackAvailable(),
                    !combat.isSpecialAttackUsed() && player.isSpecialAttackAvailable(),
                    player.getCurrentStamina() < player.getMaxStamina(),
                    false);
        }
    }
}