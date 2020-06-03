package controller;

import model.Boxer;
import model.Combat;
import model.Game;
import model.Hero;
import view.CardPanel;
import view.CombatPanel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CombatController {
    private final CombatPanel combatPanel;
    private final CardPanel cardPanel;
    private final CardLayout cardLayout;
    private Game game;
    private Combat combat;
    private Hero player;
    private Boxer enemy;

    public CombatController(CardPanel cardPanel) {
        this.cardPanel = cardPanel;
        combatPanel = cardPanel.combatPanel;
        cardLayout = cardPanel.cardLayout;

        combatPanel.btnAttack.addActionListener(e -> attack());
        combatPanel.btnSpecialAttack.addActionListener(e -> specialAttack());
        combatPanel.btnRecover.addActionListener(e -> recover());
        combatPanel.btnExitCombat.addActionListener(e -> toGameMenu());

        combatPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                runNewCombat();
            }
        });
    }

    void setGame(Game game) {
        this.game = game;
    }

    public void runNewCombat() {
        combat = game.getCombat();
        player = combat.getPlayer();
        enemy = combat.getEnemy();

        // set initial panel view
        cardPanel.updateCombatView(
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

    private void toGameMenu() {
        game.finishCombat();
        cardLayout.show(cardPanel, CardPanel.GAME_PANEL);
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
        cardPanel.updateCombatView(log,
                player.getCurrentHp(),
                player.getCurrentStamina(),
                enemy.getCurrentHp(),
                enemy.getCurrentStamina());

        if (combat.isCombatEnded()) {
            cardPanel.updateCombatButtonsView(false, false, false, true);
        } else {
            cardPanel.updateCombatButtonsView(player.isAttackAvailable(),
                    !combat.isSpecialAttackUsed() && player.isSpecialAttackAvailable(),
                    player.getCurrentStamina() < player.getMaxStamina(),
                    false);
        }
    }
}