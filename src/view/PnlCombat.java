package view;

import javax.swing.*;
import java.awt.*;

public class PnlCombat extends JPanel {
    // common
    public final JTextArea txtCombatLog;
    public final JLabel lblCombatDescription;
    public final JButton btnAttack;
    public final JButton btnSpecialAttack;
    public final JButton btnRecover;
    public final JButton btnExitCombat;

    // player
    public final JLabel lblPlayerIcon;
    public final JProgressBar prgPlayerHealth;
    public final JProgressBar prgPlayerStamina;

    // enemy
    public final JLabel lblEnemyIcon;
    public final JProgressBar prgEnemyHealth;
    public final JProgressBar prgEnemyStamina;

    public PnlCombat() {
        Dimension buttonSize;
        Dimension progressBarSize;

        // common
        txtCombatLog = new JTextArea(1, 1);
        txtCombatLog.setEditable(false);
        txtCombatLog.setLineWrap(true);
        txtCombatLog.setWrapStyleWord(true);

        lblCombatDescription = new JLabel();

        buttonSize = new Dimension(32, 32);

        btnAttack = new JButton(new ImageIcon("resources/buttons/attack.png"));
        btnAttack.setPreferredSize(buttonSize);
        btnAttack.setToolTipText("Attack");

        btnSpecialAttack = new JButton(new ImageIcon("resources/buttons/special-attack.png"));
        btnSpecialAttack.setPreferredSize(buttonSize);
        btnSpecialAttack.setToolTipText("Special attack");

        btnRecover = new JButton(new ImageIcon("resources/buttons/recover.png"));
        btnRecover.setPreferredSize(buttonSize);
        btnRecover.setToolTipText("Recover");

        btnExitCombat = new JButton(new ImageIcon("resources/buttons/exit.png"));
        btnExitCombat.setPreferredSize(buttonSize);
        btnExitCombat.setToolTipText("Leave combat");

        // player
        lblPlayerIcon = new JLabel();

        progressBarSize = new Dimension(lblPlayerIcon.getWidth(), 13);

        prgPlayerHealth = new JProgressBar();
        prgPlayerHealth.setPreferredSize(progressBarSize);
        prgPlayerHealth.setForeground(Color.GREEN);
        prgPlayerHealth.setBorderPainted(false);
        prgPlayerHealth.setBorder(BorderFactory.createEmptyBorder());

        prgPlayerStamina = new JProgressBar();
        prgPlayerStamina.setPreferredSize(progressBarSize);
        prgPlayerStamina.setForeground(Color.ORANGE);
        prgPlayerStamina.setBorderPainted(false);
        prgPlayerStamina.setBorder(BorderFactory.createEmptyBorder());

        // enemy
        lblEnemyIcon = new JLabel();

        prgEnemyHealth = new JProgressBar();
        prgEnemyHealth.setPreferredSize(progressBarSize);
        prgEnemyHealth.setForeground(Color.GREEN);
        prgEnemyHealth.setBorderPainted(false);
        prgEnemyHealth.setBorder(BorderFactory.createEmptyBorder());

        prgEnemyStamina = new JProgressBar();
        prgEnemyStamina.setPreferredSize(progressBarSize);
        prgEnemyStamina.setForeground(Color.ORANGE);
        prgEnemyStamina.setBorderPainted(false);
        prgEnemyStamina.setBorder(BorderFactory.createEmptyBorder());

        addComponents();
    }

    private void addComponents() {
        JScrollPane scrCombatLog;
        JPanel buttons;
        GroupLayout layout;

        buttons = new JPanel();
        buttons.add(btnAttack);
        buttons.add(btnSpecialAttack);
        buttons.add(btnRecover);
        buttons.add(btnExitCombat);

        scrCombatLog = new JScrollPane(txtCombatLog);

        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.linkSize(SwingConstants.VERTICAL, lblPlayerIcon, scrCombatLog);
        layout.linkSize(SwingConstants.HORIZONTAL, lblPlayerIcon, prgPlayerHealth, prgPlayerStamina);
        layout.linkSize(SwingConstants.HORIZONTAL, lblEnemyIcon, prgEnemyHealth, prgEnemyStamina);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblPlayerIcon)
                                .addComponent(prgPlayerHealth)
                                .addComponent(prgPlayerStamina))
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(scrCombatLog)
                                        .addComponent(lblCombatDescription)))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblEnemyIcon)
                                .addComponent(prgEnemyHealth)
                                .addComponent(prgEnemyStamina)))
                .addComponent(buttons));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPlayerIcon)
                                .addComponent(prgPlayerHealth)
                                .addComponent(prgPlayerStamina))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrCombatLog)
                                .addComponent(lblCombatDescription))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEnemyIcon)
                                .addComponent(prgEnemyHealth)
                                .addComponent(prgEnemyStamina)))
                .addComponent(buttons));
    }
}