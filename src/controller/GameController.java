package controller;

import model.Boxer;
import model.Game;
import model.Hero;
import view.PnlCards;
import view.PnlGame;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static controller.LogBuilder.*;

public class GameController {
    private final PnlGame pnlGame;
    private final CardLayout cardLayout;
    private final PnlCards pnlCards;
    private Game game;


    public GameController(PnlCards pnlCards) {
        this.pnlCards = pnlCards;
        this.pnlGame = pnlCards.pnlGame;
        this.cardLayout = pnlCards.cardLayout;


        pnlGame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                updateView();
            }
        });

        pnlGame.btnNewCombat.addActionListener(e -> startNewCombat());
        pnlGame.btnRest.addActionListener(e -> rest());
        pnlGame.btnCardio.addActionListener(e -> cardio());
        pnlGame.btnWorkout.addActionListener(e -> workout());
        pnlGame.btnExitGame.addActionListener(e -> finishGame());
    }

    public void startNewCombat() {
        Hero player;
        Boxer enemy;

        game.startNewCombat();

        player = game.getCombat().getPlayer();
        enemy = game.getCombat().getEnemy();

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

        cardLayout.show(pnlCards, PnlCards.COMBAT_PANEL);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void rest() {
        pnlGame.txtGameLog.append(buildRestLog(game.rest()) + "\n");
    }

    public void workout() {
        pnlGame.txtGameLog.append(buildWorkoutLog(game.workout()) + "\n");
        pnlGame.lblPlayerIcon.setToolTipText(LogBuilder.buildToolTip(game.getPlayer()));
    }

    public void cardio() {
        pnlGame.txtGameLog.append(buildCardioLog(game.cardio()) + "\n");
        pnlGame.lblPlayerIcon.setToolTipText(LogBuilder.buildToolTip(game.getPlayer()));

        if (game.isGameEnded()) {
            pnlGame.btnNewCombat.setEnabled(false);
            pnlGame.btnWorkout.setEnabled(false);
            pnlGame.btnCardio.setEnabled(false);
            pnlGame.btnExitGame.setEnabled(true);
        }
    }

    private void finishGame() {
        game.finishGame();
        cardLayout.show(pnlCards, PnlCards.START_PANEL);
    }

    private void updateView() {
        if (game.isGameEnded()) {
            pnlGame.btnNewCombat.setEnabled(false);
            pnlGame.btnRest.setEnabled(false);
            pnlGame.btnWorkout.setEnabled(false);
            pnlGame.btnCardio.setEnabled(false);
            pnlGame.btnExitGame.setEnabled(true);
        } else if (game.isRestRequired()) {
            pnlGame.btnNewCombat.setEnabled(false);
            pnlGame.btnRest.setEnabled(true);
            pnlGame.btnWorkout.setEnabled(false);
            pnlGame.btnCardio.setEnabled(false);
            pnlGame.btnExitGame.setEnabled(false);
        } else {
            pnlGame.btnNewCombat.setEnabled(true);
            pnlGame.btnRest.setEnabled(false);
            pnlGame.btnWorkout.setEnabled(true);
            pnlGame.btnCardio.setEnabled(true);
            pnlGame.btnExitGame.setEnabled(false);
        }
    }
}