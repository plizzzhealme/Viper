package view;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    public StartPanel startPanel;
    public GamePanel gamePanel;
    public CombatPanel combatPanel;
    public CardLayout cardLayout;

    public CardPanel(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        combatPanel = new CombatPanel();

        add(startPanel);
        add(gamePanel);
        add(combatPanel);
    }
}