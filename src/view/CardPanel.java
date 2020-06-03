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

    public void updateCombatView(ImageIcon playerIcon,
                                 String playerToolTip,
                                 Integer playerMaxHealth,
                                 Integer playerMaxStamina,
                                 ImageIcon enemyIcon,
                                 String enemyToolTip,
                                 Integer enemyMaxHealth,
                                 Integer enemyMaxStamina) {
        combatPanel.txtCombatLog.setText("");

        combatPanel.lblPlayerIcon.setIcon(playerIcon);
        combatPanel.lblPlayerIcon.setToolTipText(playerToolTip);
        combatPanel.prgPlayerHealth.setMaximum(playerMaxHealth);
        combatPanel.prgPlayerHealth.setValue(playerMaxHealth);
        combatPanel.prgPlayerStamina.setMaximum(playerMaxStamina);
        combatPanel.prgPlayerStamina.setValue(playerMaxStamina);

        combatPanel.lblEnemyIcon.setIcon(enemyIcon);
        combatPanel.lblEnemyIcon.setToolTipText(enemyToolTip);
        combatPanel.prgEnemyHealth.setMaximum(enemyMaxHealth);
        combatPanel.prgEnemyHealth.setValue(enemyMaxHealth);
        combatPanel.prgEnemyStamina.setMaximum(enemyMaxStamina);
        combatPanel.prgEnemyStamina.setValue(enemyMaxStamina);

    }

    public void updateCombatView(String log,
                                 Integer playerHealth,
                                 Integer playerStamina,
                                 Integer enemyHealth,
                                 Integer enemyStamina) {
        combatPanel.txtCombatLog.append(log + "\n");
        combatPanel.prgPlayerHealth.setValue(playerHealth);
        combatPanel.prgPlayerStamina.setValue(playerStamina);
        combatPanel.prgEnemyHealth.setValue(enemyHealth);
        combatPanel.prgEnemyStamina.setValue(enemyStamina);
    }

    public void updateCombatButtonsView(boolean attack,
                                        boolean specialAttack,
                                        boolean recover,
                                        boolean exit) {
        combatPanel.btnAttack.setEnabled(attack);
        combatPanel.btnSpecialAttack.setEnabled(specialAttack);
        combatPanel.btnRecover.setEnabled(recover);
        combatPanel.btnExitCombat.setEnabled(exit);
    }
}