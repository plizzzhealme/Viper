package io.github.plizzzhealme.controller;

import io.github.plizzzhealme.model.Game;
import io.github.plizzzhealme.model.character.Boxer;
import io.github.plizzzhealme.model.character.Hero;
import io.github.plizzzhealme.view.Frame;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class StartController {
    private final GameController gameController;
    private final CombatController combatController;
    private final CardLayout cardLayout;
    private final Frame frame;

    public StartController() {
        List<String> heroes;

        frame = new Frame();
        cardLayout = frame.cardLayout;

        gameController = new GameController(frame);
        combatController = new CombatController(frame);

        heroes = Game.heroes.stream().map(Boxer::getName).collect(Collectors.toList());
        frame.addHeroes(heroes);

        frame.addStartPanelListeners(e -> selectHero(), e -> startNewGame());
    }

    private void selectHero() {
        int i;
        Hero hero;

        i = frame.getSelectedHero();
        hero = Game.heroes.get(i);

        frame.updateStartView(hero.getIcon(), LogBuilder.buildToolTip(hero));
    }

    private void startNewGame() {
        int i;
        Game game;

        game = new Game();
        i = frame.getSelectedHero();
        game.selectHero(i);
        gameController.setGame(game);
        combatController.setGame(game);

        frame.updateGameView(LogBuilder.buildGameLog(game),
                game.getPlayer().getIcon(),
                LogBuilder.buildToolTip(game.getPlayer()),
                game.getPlayer().getName(),
                game.getCurrentEnemy().getIcon(),
                LogBuilder.buildToolTip(game.getCurrentEnemy()),
                game.getCurrentEnemy().getName()
        );

        frame.updateGameButtonsView(true,
                false,
                true,
                true,
                true);

        cardLayout.show(frame, Frame.GAME_PANEL);
    }
}