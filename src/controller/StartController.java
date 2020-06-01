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
        cardLayout = new CardLayout();
        cardPanel = new CardPanel(cardLayout);

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
        int i;

        i = startPanel.cmbHeroes.getSelectedIndex();
        Game game = new Game();
        game.selectHero(i);
        gameController.setGame(game);
        gameController.runNewGame();
        combatController.setGame(game);
        cardLayout.next(cardPanel);
    }
}