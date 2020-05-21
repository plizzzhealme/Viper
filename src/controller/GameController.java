package controller;

import model.Game;
import view.CombatPanel;
import view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static controller.LogBuilder.*;

public class GameController {
    final GamePanel gamePanel;
    final CombatPanel combatPanel;
    final CombatController combatController;
    final CardLayout cardLayout;
    final JPanel panels;
    final Game game;
    final List<ImageIcon> enemyIcons;

    public GameController(Game game,
                          CombatPanel combatPanel,
                          GamePanel gamePanel,
                          CardLayout cardLayout,
                          JPanel panels, List<ImageIcon> enemyIcons) {
        this.combatPanel = combatPanel;
        this.gamePanel = gamePanel;
        this.cardLayout = cardLayout;
        this.panels = panels;
        this.game = game;
        this.enemyIcons = enemyIcons;
        combatController = new CombatController(combatPanel);
    }

    void run() {
        gamePanel.lblEnemyIcon.setIcon(enemyIcons.get(0));
        gamePanel.lblPlayerName.setText(game.getPlayer().getName());

        gamePanel.btnRest.setEnabled(false);
        gamePanel.btnNewCombat.setEnabled(true);
        gamePanel.btnWorkout.setEnabled(true);
        gamePanel.btnCardio.setEnabled(true);

        combatPanel.lblPlayerIcon.setIcon(gamePanel.lblPlayerIcon.getIcon());

        addActionListeners();
    }

    private void addActionListeners() {
        gamePanel.btnNewCombat.addActionListener(e -> startNewCombat());
        gamePanel.btnRest.addActionListener(e -> rest());
        gamePanel.btnCardio.addActionListener(e -> cardio());
        gamePanel.btnWorkout.addActionListener(e -> workout());
        gamePanel.btnExitGame.addActionListener(e -> finishGame());
        combatPanel.btnExitCombat.addActionListener(e -> finishCombat());
    }

    public void startNewCombat() {
        combatPanel.txtCombatLog.setText("");

        combatPanel.lblEnemyIcon.setIcon(gamePanel.lblEnemyIcon.getIcon());
        combatPanel.lblEnemyIcon.setToolTipText(LogBuilder.buildToolTip(game.getCurrentEnemy()));

        combatPanel.prgPlayerHealth.setMaximum(game.getPlayer().getMaxHp());
        combatPanel.prgPlayerHealth.setValue(game.getPlayer().getCurrentHp());

        combatPanel.prgPlayerStamina.setMaximum(game.getPlayer().getMaxStamina());
        combatPanel.prgPlayerStamina.setValue(game.getPlayer().getCurrentStamina());

        combatPanel.prgEnemyHealth.setMaximum(game.getCurrentEnemy().getMaxHp());
        combatPanel.prgEnemyHealth.setValue(game.getCurrentEnemy().getCurrentHp());

        combatPanel.prgEnemyStamina.setMaximum(game.getCurrentEnemy().getMaxStamina());
        combatPanel.prgEnemyStamina.setValue(game.getCurrentEnemy().getCurrentStamina());

        game.startNewCombat();

        combatController.run(game.getCombat());

        cardLayout.next(panels);
    }

    private void finishCombat() {
        game.finishCombat();
        if (!game.isGameEnded()) {
            gamePanel.lblEnemyIcon.setToolTipText(LogBuilder.buildToolTip(game.getCurrentEnemy()));
        }
        gamePanel.txtGameLog.append("combat ended\n");
        cardLayout.previous(panels);

        gamePanel.btnRest.setEnabled(!game.isGameEnded());
        gamePanel.btnNewCombat.setEnabled(false);
        gamePanel.btnWorkout.setEnabled(false);
        gamePanel.btnCardio.setEnabled(false);
    }

    public void rest() {
        gamePanel.txtGameLog.append(buildRestLog(game.rest()) + "\n");

        gamePanel.btnRest.setEnabled(false);

        if (!game.isGameEnded()) {
            gamePanel.btnNewCombat.setEnabled(true);
            gamePanel.btnWorkout.setEnabled(true);
            gamePanel.btnCardio.setEnabled(true);
        }
    }

    public void cardio() {
        gamePanel.txtGameLog.append(buildCardioLog(game.cardio()) + "\n");
        gamePanel.lblPlayerIcon.setToolTipText(LogBuilder.buildToolTip(game.getPlayer()));

        if (game.isGameEnded()) {
            gamePanel.btnNewCombat.setEnabled(false);
            gamePanel.btnWorkout.setEnabled(false);
            gamePanel.btnCardio.setEnabled(false);
        }
    }

    public void workout() {
        gamePanel.txtGameLog.append(buildWorkoutLog(game.workout()) + "\n");
        gamePanel.lblPlayerIcon.setToolTipText(LogBuilder.buildToolTip(game.getPlayer()));

        if (game.isGameEnded()) {
            gamePanel.btnNewCombat.setEnabled(false);
            gamePanel.btnWorkout.setEnabled(false);
            gamePanel.btnCardio.setEnabled(false);
        }
    }

    private void finishGame() {
        game.finishGame();
        cardLayout.previous(panels);
    }
}