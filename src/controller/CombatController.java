package controller;

import model.Game;
import view.CardPanel;
import view.CombatPanel;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CombatController {
    private final CombatPanel combatPanel;
    private final CardPanel cardPanel;
    private final CardLayout cardLayout;
    private Game game;

    public CombatController(CardPanel cardPanel) {
        this.cardPanel = cardPanel;
        combatPanel = cardPanel.combatPanel;
        cardLayout = cardPanel.cardLayout;

        combatPanel.btnAttack.addActionListener(e -> attack());
        combatPanel.btnSpecialAttack.addActionListener(e -> specialAttack());
        combatPanel.btnRecover.addActionListener(e -> recover());
        combatPanel.btnExitCombat.addActionListener(e -> toGameMenu());
        combatPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                runNewCombat();
            }
        });
    }

    void setGame(Game game) {
        this.game = game;
    }

    public void runNewCombat() {
        combatPanel.lblPlayerIcon.setIcon(game.getCombat().getPlayer().getIcon());
        combatPanel.txtCombatLog.setText("");

        combatPanel.lblEnemyIcon.setIcon(game.
                getCombat().
                getEnemy().
                getIcon());
        combatPanel.lblEnemyIcon.setToolTipText(LogBuilder.buildToolTip(game.getCurrentEnemy()));

        combatPanel.prgPlayerHealth.setMaximum(game.getPlayer().getMaxHp());
        combatPanel.prgPlayerHealth.setValue(game.getPlayer().getCurrentHp());

        combatPanel.prgPlayerStamina.setMaximum(game.getPlayer().getMaxStamina());
        combatPanel.prgPlayerStamina.setValue(game.getPlayer().getCurrentStamina());

        combatPanel.prgEnemyHealth.setMaximum(game.getCurrentEnemy().getMaxHp());
        combatPanel.prgEnemyHealth.setValue(game.getCurrentEnemy().getCurrentHp());

        combatPanel.prgEnemyStamina.setMaximum(game.getCurrentEnemy().getMaxStamina());
        combatPanel.prgEnemyStamina.setValue(game.getCurrentEnemy().getCurrentStamina());
        updateView();
    }

    private void attack() {
        printCombatLog(game.getCombat().makeAttackTurn());
    }

    private void specialAttack() {
        printCombatLog(game.getCombat().makeSpecialAttackTurn());
    }

    private void recover() {
        printCombatLog(game.getCombat().makeRecoveryTurn());
    }

    private void toGameMenu() {
        game.finishCombat();
        cardLayout.previous(cardPanel);
    }

    private void printCombatLog(int result) {
        combatPanel.txtCombatLog.append(LogBuilder.buildTurnLog(result,
                game.getCombat().getPlayer().getName(),
                game.getCombat().getEnemy().getName()));
        combatPanel.txtCombatLog.append("\n");

        if (!game.getCombat().isCombatEnded()) {
            result = game.getCombat().makeBotTurn();
            combatPanel.txtCombatLog.append(LogBuilder.buildTurnLog(result,
                    game.getCombat().getEnemy().getName(),
                    game.getCombat().getPlayer().getName()));
            combatPanel.txtCombatLog.append("\n");
        }
        updateView();
    }

    private void updateView() {
        combatPanel.prgPlayerHealth.setValue(game.getCombat().getPlayer().getCurrentHp());
        combatPanel.prgPlayerStamina.setValue(game.getCombat().getPlayer().getCurrentStamina());

        combatPanel.prgEnemyHealth.setValue(game.getCombat().getEnemy().getCurrentHp());
        combatPanel.prgEnemyStamina.setValue(game.getCombat().getEnemy().getCurrentStamina());

        combatPanel.btnAttack.setEnabled(game.getCombat().getPlayer().isAttackAvailable()
                && !game.getCombat().isCombatEnded());
        combatPanel.btnSpecialAttack.setEnabled(!game.getCombat().isSpecialAttackUsed()
                && game.getCombat().getPlayer().isSpecialAttackAvailable()
                && !game.getCombat().isCombatEnded());
        combatPanel.btnRecover.setEnabled(game.getCombat().getPlayer().getCurrentStamina() < game.getCombat().getPlayer().getMaxStamina()
                && !game.getCombat().isCombatEnded());
        combatPanel.btnExitCombat.setEnabled(game.getCombat().isCombatEnded());
    }
}