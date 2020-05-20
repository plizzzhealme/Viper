package controller;

import model.Boxer;
import model.Game;
import model.Hero;
import view.CombatPanel;
import view.GamePanel;
import view.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StartController {
    static final String HERO_SELECTION_PANEL = "1";
    static final String GAME_PANEL = "2";
    static final String COMBAT_PANEL = "3";
    private final JFrame frame;

    private final StartPanel startPanel;
    private final GamePanel gamePanel;
    private final CombatPanel combatPanel;
    private final CardLayout cardLayout;
    private final JPanel cards;

    private final List<Hero> heroes;
    private final List<Boxer> enemies;

    final List<ImageIcon> heroIcons;
    final List<ImageIcon> enemyIcons;

    public StartController(JFrame frame, List<Hero> heroes, List<ImageIcon> heroIcons,
                           List<Boxer> enemies, List<ImageIcon> enemyIcons) {
        this.frame = frame;
        this.heroes = heroes;
        this.heroIcons = heroIcons;
        this.enemies = enemies;
        this.enemyIcons = enemyIcons;

        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        combatPanel = new CombatPanel();
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);


    }

    public void run() {
        // add panels
        cards.add(startPanel, HERO_SELECTION_PANEL);
        cards.add(gamePanel, GAME_PANEL);
        cards.add(combatPanel, COMBAT_PANEL);

        // fill combobox
        for (Hero hero : heroes) {
            startPanel.cmbHeroList.addItem(hero.getName());
        }

        startPanel.lblHeroIcon.setIcon(heroIcons.get(0));
        startPanel.lblHeroIcon.setToolTipText(LogBuilder.buildToolTip(heroes.get(0)));
        addActionListeners();
        frame.setContentPane(cards);
        frame.setVisible(true);
    }

    private void addActionListeners() {
        startPanel.cmbHeroList.addActionListener(e -> selectHero());
        startPanel.btnSelectHero.addActionListener(e -> startNewGame());


    }

    private void selectHero() {
        int i = startPanel.cmbHeroList.getSelectedIndex();
        startPanel.lblHeroIcon.setIcon(heroIcons.get(i));
        startPanel.lblHeroIcon.setToolTipText(LogBuilder.buildToolTip(heroes.get(i)));
    }

    private void startNewGame() {
        int i = startPanel.cmbHeroList.getSelectedIndex();
        Hero player = heroes.get(i);
        String nickname = JOptionPane.showInputDialog(frame, "Enter your name");
        gamePanel.lblPlayerIcon.setIcon(heroIcons.get(i));

        if (nickname != null) {
            Game game = new Game(nickname, player, enemies, 40, 2);
            gamePanel.lblPlayerIcon.setIcon(heroIcons.get(i));
            GameController gameController = new GameController(game, combatPanel, gamePanel, cardLayout, cards, enemyIcons);
            gameController.run();
            cardLayout.show(cards, GAME_PANEL);
        }
    }
}