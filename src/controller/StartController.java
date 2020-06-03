package controller;

import model.Boxer;
import model.Game;
import model.Hero;
import view.PnlCards;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class StartController {
    private final GameController gameController;
    private final CombatController combatController;
    private final CardLayout cardLayout;
    private final PnlCards pnlCards;

    public StartController() {
        List<String> heroes;

        pnlCards = new PnlCards();
        cardLayout = pnlCards.cardLayout;

        gameController = new GameController(pnlCards);
        combatController = new CombatController(pnlCards);

        heroes = Game.heroes.stream().map(Boxer::getName).collect(Collectors.toList());
        pnlCards.addHeroes(heroes);

        pnlCards.addStartPanelListeners(e -> selectHero(), e -> startNewGame());
    }

    private void selectHero() {
        int i;
        Hero hero;

        i = pnlCards.getSelectedHero();
        hero = Game.heroes.get(i);

        pnlCards.updateStartView(hero.getIcon(), LogBuilder.buildToolTip(hero));
    }

    private void startNewGame() {
        int i;
        Game game;

        game = new Game();
        i = pnlCards.getSelectedHero();
        game.selectHero(i);
        gameController.setGame(game);
        combatController.setGame(game);

        pnlCards.updateGameView(LogBuilder.buildGameLog(game),
                game.getPlayer().getIcon(),
                LogBuilder.buildToolTip(game.getPlayer()),
                game.getPlayer().getName(),
                game.getCurrentEnemy().getIcon(),
                LogBuilder.buildToolTip(game.getCurrentEnemy()),
                game.getCurrentEnemy().getName()
        );

        pnlCards.updateGameButtonsView(true,
                false,
                true,
                true,
                true);

        cardLayout.show(pnlCards, PnlCards.GAME_PANEL);
    }
}