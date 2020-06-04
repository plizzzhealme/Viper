package io.github.plizzzhealme.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Frame extends JPanel {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 375;
    public static final String START_PANEL = "1";
    public static final String GAME_PANEL = "2";
    public static final String COMBAT_PANEL = "3";
    public PnlStart pnlStart;
    public PnlGame pnlGame;
    public PnlCombat combatPanel;
    public CardLayout cardLayout;

    public Frame() {
        JFrame frame;

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        pnlStart = new PnlStart();
        pnlGame = new PnlGame();
        combatPanel = new PnlCombat();

        add(pnlStart, START_PANEL);
        add(pnlGame, GAME_PANEL);
        add(combatPanel, COMBAT_PANEL);

        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setIconImage(new ImageIcon("data/logo-icon.png").getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);
    }

    public void addHeroes(List<String> heroes) {
        for (String hero : heroes) {
            pnlStart.cmbHeroes.addItem(hero);
        }
    }

    public void updateStartView(ImageIcon heroIcon, String heroToolTip) {
        pnlStart.lblHeroIcon.setIcon(heroIcon);
        pnlStart.lblHeroIcon.setToolTipText(heroToolTip);
    }

    public int getSelectedHero() {
        return pnlStart.cmbHeroes.getSelectedIndex();
    }

    public void addStartPanelListeners(ActionListener selectHero,
                                       ActionListener startNewGame) {
        pnlStart.cmbHeroes.addActionListener(selectHero);
        pnlStart.btnSelectHero.addActionListener(startNewGame);
    }

    public void updateGameView(String gameInfo,
                               ImageIcon playerIcon,
                               String playerToolTip,
                               String playerName,
                               ImageIcon enemyIcon,
                               String enemyTooltip,
                               String enemyName) {
        pnlGame.lblGameProgress.setText(gameInfo);
        pnlGame.lblPlayerIcon.setIcon(playerIcon);
        pnlGame.lblPlayerIcon.setToolTipText(playerToolTip);
        pnlGame.lblPlayerName.setText(playerName);
        pnlGame.lblEnemyIcon.setIcon(enemyIcon);
        pnlGame.lblEnemyIcon.setToolTipText(enemyTooltip);
        pnlGame.lblEnemyName.setText(enemyName);
    }

    public void updateGameButtonsView(boolean newCombat,
                                      boolean rest,
                                      boolean workout,
                                      boolean cardio,
                                      boolean exit) {
        pnlGame.btnNewCombat.setEnabled(newCombat);
        pnlGame.btnRest.setEnabled(rest);
        pnlGame.btnWorkout.setEnabled(workout);
        pnlGame.btnCardio.setEnabled(cardio);
        pnlGame.btnExitGame.setEnabled(exit);
    }

    public void addCombatPanelListeners(ActionListener attack,
                                        ActionListener specialAttack,
                                        ActionListener recover,
                                        ActionListener exit) {
        combatPanel.btnAttack.addActionListener(attack);
        combatPanel.btnSpecialAttack.addActionListener(specialAttack);
        combatPanel.btnRecover.addActionListener(recover);
        combatPanel.btnExitCombat.addActionListener(exit);
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