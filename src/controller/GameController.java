package controller;

import model.Game;
import view.CardPanel;
import view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static controller.LogBuilder.*;

public class GameController {
    private final GamePanel gamePanel;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private Game game;


    public GameController(CardPanel cardPanel) {
        this.cardPanel = cardPanel;
        this.gamePanel = cardPanel.gamePanel;
        this.cardLayout = cardPanel.cardLayout;


        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                updateView();
            }
        });

        gamePanel.btnNewCombat.addActionListener(e -> startNewCombat());
        gamePanel.btnRest.addActionListener(e -> rest());
        gamePanel.btnCardio.addActionListener(e -> cardio());
        gamePanel.btnWorkout.addActionListener(e -> workout());
        gamePanel.btnExitGame.addActionListener(e -> finishGame());
    }

    public void runNewGame() {
        gamePanel.lblPlayerIcon.setIcon(game.getPlayer().getIcon());
        gamePanel.lblPlayerName.setText(game.getPlayer().getName());
        gamePanel.lblEnemyIcon.setIcon(game.getCurrentEnemy().getIcon());
        gamePanel.lblGameProgress.setText(LogBuilder.buildGameLog(game));

        gamePanel.btnRest.setEnabled(false);
        gamePanel.btnNewCombat.setEnabled(true);
        gamePanel.btnWorkout.setEnabled(true);
        gamePanel.btnCardio.setEnabled(true);
    }

    public void startNewCombat() {
        game.startNewCombat();
        cardLayout.show(cardPanel, CardPanel.COMBAT_PANEL);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void rest() {
        gamePanel.txtGameLog.append(buildRestLog(game.rest()) + "\n");
    }

    public void workout() {
        gamePanel.txtGameLog.append(buildWorkoutLog(game.workout()) + "\n");
        gamePanel.lblPlayerIcon.setToolTipText(LogBuilder.buildToolTip(game.getPlayer()));
    }

    public void cardio() {
        gamePanel.txtGameLog.append(buildCardioLog(game.cardio()) + "\n");
        gamePanel.lblPlayerIcon.setToolTipText(LogBuilder.buildToolTip(game.getPlayer()));

        if (game.isGameEnded()) {
            gamePanel.btnNewCombat.setEnabled(false);
            gamePanel.btnWorkout.setEnabled(false);
            gamePanel.btnCardio.setEnabled(false);
            gamePanel.btnExitGame.setEnabled(true);
        }
    }

    private void finishGame() {
        game.finishGame();
        cardLayout.show(cardPanel, CardPanel.START_PANEL);
    }

    private void updateView() {
        if (game.isGameEnded()) {
            gamePanel.btnNewCombat.setEnabled(false);
            gamePanel.btnRest.setEnabled(false);
            gamePanel.btnWorkout.setEnabled(false);
            gamePanel.btnCardio.setEnabled(false);
            gamePanel.btnExitGame.setEnabled(true);
        } else if (game.isRestRequired()) {
            gamePanel.btnNewCombat.setEnabled(false);
            gamePanel.btnRest.setEnabled(true);
            gamePanel.btnWorkout.setEnabled(false);
            gamePanel.btnCardio.setEnabled(false);
            gamePanel.btnExitGame.setEnabled(false);
        } else {
            gamePanel.btnNewCombat.setEnabled(true);
            gamePanel.btnRest.setEnabled(false);
            gamePanel.btnWorkout.setEnabled(true);
            gamePanel.btnCardio.setEnabled(true);
            gamePanel.btnExitGame.setEnabled(false);
        }
    }
}