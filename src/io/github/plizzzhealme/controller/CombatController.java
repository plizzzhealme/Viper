package io.github.plizzzhealme.controller;

import io.github.plizzzhealme.model.Game;
import io.github.plizzzhealme.model.combat.Turn;
import io.github.plizzzhealme.view.Frame;

import java.awt.*;

public class CombatController {
    private final Frame frame;
    private final CardLayout cardLayout;
    private Game game;

    public CombatController(Frame frame) {
        this.frame = frame;
        cardLayout = frame.cardLayout;

        frame.addCombatPanelListeners(e -> attack(),
                e -> specialAttack(),
                e -> recover(),
                e -> exit());
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void attack() {
        Turn turn;
        String log;

        turn = game.getCombat().makeAttackTurn();
        log = LogBuilder.buildTurnLog(turn, game.getCombat().getPlayer(), game.getCombat().getEnemy());
        updateView(log);
    }

    private void specialAttack() {
        Turn turn;
        String log;

        turn = game.getCombat().makeSpecialAttackTurn();
        log = LogBuilder.buildTurnLog(turn, game.getCombat().getPlayer(), game.getCombat().getEnemy());
        updateView(log);
    }

    private void recover() {
        Turn turn;
        String log;

        turn = game.getCombat().makeRecoveryTurn();
        log = LogBuilder.buildTurnLog(turn, game.getCombat().getPlayer(), game.getCombat().getEnemy());
        updateView(log);
    }

    private void exit() {
        game.finishCombat();
        cardLayout.show(frame, Frame.GAME_PANEL);
        frame.updateGameView(LogBuilder.buildGameLog(game),
                game.getPlayer().getIcon(),
                LogBuilder.buildToolTip(game.getPlayer()),
                game.getPlayer().getName(),
                game.getCurrentEnemy().getIcon(),
                LogBuilder.buildToolTip(game.getCurrentEnemy()),
                game.getCurrentEnemy().getName()
        );

    }


    private void updateView(String log) {
        frame.updateCombatView(log,
                game.getCombat().getPlayer().getCurrentHealth(),
                game.getCombat().getPlayer().getCurrentStamina(),
                game.getCombat().getEnemy().getCurrentHealth(),
                game.getCombat().getEnemy().getCurrentStamina());

        if (game.getCombat().isCombatEnded()) {
            frame.updateCombatButtonsView(false, false, false, true);
        } else {
            frame.updateCombatButtonsView(game.getCombat().getPlayer().isAttackAvailable(),
                    !game.getCombat().isSpecialAttackUsed()
                            && game.getCombat().getPlayer().isSpecialAttackAvailable(),
                    game.getCombat().getPlayer().getCurrentStamina()
                            < game.getCombat().getPlayer().getMaxStamina(),
                    false);
        }
    }
}