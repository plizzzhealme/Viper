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
    private final JFrame frame;

    private final StartPanel startPanel;
    private final GamePanel gamePanel;
    private final CombatPanel combatPanel;
    private final JPanel panels;
    private final CardLayout layout;

    private final List<Hero> heroes;
    private final List<Boxer> enemies;

    private final List<ImageIcon> heroIcons;
    private final List<ImageIcon> enemyIcons;

    private final List<String> intro;

    public StartController(JFrame frame,
                           List<String> intro,
                           List<Hero> heroes,
                           List<ImageIcon> heroIcons,
                           List<Boxer> enemies,
                           List<ImageIcon> enemyIcons) {
        this.frame = frame;
        this.intro = intro;
        this.heroes = heroes;
        this.heroIcons = heroIcons;
        this.enemies = enemies;
        this.enemyIcons = enemyIcons;

        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        combatPanel = new CombatPanel();
        layout = new CardLayout();
        panels = new JPanel(layout);
    }

    public void run() {
        // add panels to cards
        panels.add(startPanel);
        panels.add(gamePanel);
        panels.add(combatPanel);

        // print intro
        for (String line : intro) {
            startPanel.txtInfo.append(line + "\n");
        }

        // fill hero selection combobox
        for (Hero hero : heroes) {
            startPanel.cmbHeroes.addItem(hero.getName());
        }

        // set initial hero icon and tooltip
        startPanel.lblHeroIcon.setIcon(heroIcons.get(0));
        startPanel.lblHeroIcon.setToolTipText(LogBuilder.buildToolTip(heroes.get(0)));

        frame.setContentPane(panels);
        frame.setVisible(true);

        addActionListeners();
    }

    private void addActionListeners() {
        startPanel.cmbHeroes.addActionListener(e -> selectHero());
        startPanel.btnSelectHero.addActionListener(e -> startNewGame());
    }

    private void selectHero() {
        int i = startPanel.cmbHeroes.getSelectedIndex();
        startPanel.lblHeroIcon.setIcon(heroIcons.get(i));
        startPanel.lblHeroIcon.setToolTipText(LogBuilder.buildToolTip(heroes.get(i)));
    }

    private void startNewGame() {
        int index;
        String nickname;
        Hero selectedHero;
        GameController gameController;

        index = startPanel.cmbHeroes.getSelectedIndex();
        selectedHero = heroes.get(index);
        nickname = JOptionPane.showInputDialog(frame, "Enter your name");
        gamePanel.lblPlayerIcon.setIcon(heroIcons.get(index));

        if (nickname != null) {
            Game game = new Game(nickname, selectedHero, enemies, 40, 2);
            gamePanel.lblPlayerIcon.setIcon(heroIcons.get(index));
            gameController = new GameController(game, combatPanel, gamePanel, layout, panels, enemyIcons);
            gameController.run();
            layout.next(panels);
        }
    }
}