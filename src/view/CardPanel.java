package view;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    public static final String START_PANEL = "1";
    public static final String GAME_PANEL = "2";
    public static final String COMBAT_PANEL = "3";
    public StartPanel startPanel;
    public GamePanel gamePanel;
    public CombatPanel combatPanel;
    public CardLayout cardLayout;

    public CardPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        combatPanel = new CombatPanel();

        add(startPanel, START_PANEL);
        add(gamePanel, GAME_PANEL);
        add(combatPanel, COMBAT_PANEL);
    }
}