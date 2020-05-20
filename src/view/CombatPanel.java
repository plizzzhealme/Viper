package view;

import javax.swing.*;
import java.awt.*;

public class CombatPanel extends JPanel {
    // left
    public final JLabel lblPlayerIcon;
    public final JProgressBar prgPlayerHealth;
    public final JProgressBar prgPlayerStamina;
    public final JLabel lblPlayerName;

    // center
    private final JScrollPane scrCombatLog;
    public final JTextArea txtCombatLog;

    // right
    public final JLabel lblEnemyIcon;
    public final JProgressBar prgEnemyHealth;
    public final JProgressBar prgEnemyStamina;
    public final JLabel lblEnemyName;

    // bottom
    public final JButton btnAttack;
    public final JButton btnSpecialAttack;
    public final JButton btnRecover;
    public final JButton btnExitCombat;

    public CombatPanel() {
        Dimension buttonSize;

        // left
        lblPlayerIcon = new JLabel();
        prgPlayerHealth = new JProgressBar();
        prgPlayerHealth.setPreferredSize(new Dimension(120, 10));
        prgPlayerStamina = new JProgressBar();
        prgPlayerStamina.setPreferredSize(new Dimension(120, 10));
        lblPlayerName = new JLabel();

        // center
        txtCombatLog = new JTextArea(1, 1);
        txtCombatLog.setEditable(false);
        txtCombatLog.setLineWrap(true);
        txtCombatLog.setWrapStyleWord(true);
        scrCombatLog = new JScrollPane(txtCombatLog);

        // right
        lblEnemyIcon = new JLabel();
        prgEnemyHealth = new JProgressBar();
        prgEnemyHealth.setPreferredSize(new Dimension(120, 10));
        prgEnemyStamina = new JProgressBar();
        prgEnemyStamina.setPreferredSize(new Dimension(120, 10));
        lblEnemyName = new JLabel();

        // bottom
        buttonSize = new Dimension(32, 32);

        btnAttack = new JButton(new ImageIcon("data/buttons/attack.png"));
        btnAttack.setPreferredSize(buttonSize);
        btnAttack.setToolTipText("Attack");

        btnSpecialAttack = new JButton(new ImageIcon("data/buttons/special-attack.png"));
        btnSpecialAttack.setPreferredSize(buttonSize);
        btnSpecialAttack.setToolTipText("Special attack");

        btnRecover = new JButton(new ImageIcon("data/buttons/recover.png"));
        btnRecover.setPreferredSize(buttonSize);
        btnRecover.setToolTipText("Recover");

        btnExitCombat = new JButton(new ImageIcon("data/buttons/exit.png"));
        btnExitCombat.setPreferredSize(buttonSize);
        btnExitCombat.setToolTipText("Leave combat");

        addComponents();
    }

    private void addComponents() {
        GroupLayout layout;
        JPanel buttons;

        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        buttons = new JPanel();
        buttons.add(btnAttack);
        buttons.add(btnSpecialAttack);
        buttons.add(btnRecover);
        buttons.add(btnExitCombat);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblPlayerIcon)
                        .addComponent(prgPlayerHealth)
                        .addComponent(prgPlayerStamina))
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(scrCombatLog)
                                .addComponent(buttons)))
                .addGroup(layout.createParallelGroup()
                                .addComponent(lblEnemyIcon)
                                .addComponent(prgEnemyHealth)
                                .addComponent(prgEnemyStamina)));

        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlayerIcon)
                        .addComponent(prgPlayerHealth)
                        .addComponent(prgPlayerStamina))
                .addGroup(layout.createSequentialGroup()
                                .addComponent(scrCombatLog)
                                .addComponent(buttons))
                 .addGroup(layout.createSequentialGroup()
                         .addComponent(lblEnemyIcon)
                         .addComponent(prgEnemyHealth)
                         .addComponent(prgEnemyStamina)));

        layout.linkSize(SwingConstants.VERTICAL, lblPlayerIcon, scrCombatLog);
        layout.linkSize(SwingConstants.HORIZONTAL, lblPlayerIcon, prgPlayerStamina, prgPlayerHealth);
        layout.linkSize(SwingConstants.HORIZONTAL, lblEnemyIcon, prgEnemyStamina, prgEnemyHealth);


    }
}