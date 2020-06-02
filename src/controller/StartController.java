package controller;

import model.Game;
import model.Hero;
import view.CardPanel;
import view.StartPanel;

import javax.swing.*;
import java.awt.*;

public class StartController {
    private final GameController gameController;
    private final CombatController combatController;
    private final CardLayout cardLayout;
    private final CardPanel cardPanel;
    private final StartPanel startPanel;


    public StartController() {
        cardPanel = new CardPanel();
        cardLayout = cardPanel.cardLayout;
        startPanel = cardPanel.startPanel;

        gameController = new GameController(cardPanel);
        combatController = new CombatController(cardPanel);

        for (Hero hero : Game.heroes) {
            startPanel.cmbHeroes.addItem(hero.getName());
        }

        startPanel.lblHeroIcon.setIcon(Game.heroes.get(0).getIcon());
        startPanel.lblHeroIcon.setToolTipText(LogBuilder.buildToolTip(Game.heroes.get(0)));

        startPanel.cmbHeroes.addActionListener(e -> selectHero());
        startPanel.btnSelectHero.addActionListener(e -> startNewGame());
    }

    public JPanel getCards() {
        return cardPanel;
    }

    private void selectHero() {
        int i;

        i = startPanel.cmbHeroes.getSelectedIndex();
        startPanel.lblHeroIcon.setIcon(Game.heroes.get(i).getIcon());
        startPanel.lblHeroIcon.setToolTipText(LogBuilder.buildToolTip(Game.heroes.get(i)));
    }

    private void startNewGame() {
        Game game = new Game();
        game.selectHero(startPanel.cmbHeroes.getSelectedIndex());
        gameController.setGame(game);
        combatController.setGame(game);
        gameController.runNewGame();
        cardLayout.show(cardPanel, CardPanel.GAME_PANEL);
    }
}