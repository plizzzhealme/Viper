package view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // common
    public final JTextArea txtGameLog;
    public final JLabel lblGameProgress;
    public final JButton btnNewCombat;
    public final JButton btnRest;
    public final JButton btnWorkout;
    public final JButton btnCardio;
    public final JButton btnExitGame;

    // player
    public final JLabel lblPlayerIcon;
    public final JLabel lblPlayerName;

    // enemy
    public final JLabel lblEnemyIcon;
    public final JLabel lblEnemyName;

    public GamePanel() {
        Dimension buttonSize;

        // common
        lblPlayerName = new JLabel();
        lblPlayerIcon = new JLabel();
        txtGameLog = new JTextArea(1, 1);
        txtGameLog.setEditable(false);
        txtGameLog.setLineWrap(true);
        txtGameLog.setWrapStyleWord(true);
        lblGameProgress = new JLabel("<html><p>Game progress: <b>5</b></p></html>");
        lblEnemyName = new JLabel("enemy");
        lblEnemyIcon = new JLabel();
        buttonSize = new Dimension(32, 32);

        btnNewCombat = new JButton(new ImageIcon("resources/buttons/start-combat.png"));
        btnNewCombat.setPreferredSize(buttonSize);
        btnNewCombat.setToolTipText("New combat");

        btnRest = new JButton(new ImageIcon("resources/buttons/rest.png"));
        btnRest.setPreferredSize(buttonSize);
        btnRest.setToolTipText("Rest");

        btnWorkout = new JButton(new ImageIcon("resources/buttons/workout.png"));
        btnWorkout.setPreferredSize(buttonSize);
        btnWorkout.setToolTipText("Workout");

        btnCardio = new JButton(new ImageIcon("resources/buttons/cardio.png"));
        btnCardio.setPreferredSize(buttonSize);
        btnCardio.setToolTipText("Cardio");

        btnExitGame = new JButton(new ImageIcon("resources/buttons/exit.png"));
        btnExitGame.setPreferredSize(buttonSize);
        btnExitGame.setToolTipText("Exit");

        addComponents();
    }

    private void addComponents() {
        JScrollPane scrGameLog;
        JPanel gameProgress;
        JPanel buttons;
        JPanel playerName;
        JPanel enemyName;
        GroupLayout layout;

        scrGameLog = new JScrollPane(txtGameLog);

        gameProgress = new JPanel();
        gameProgress.add(lblGameProgress);

        buttons = new JPanel();
        buttons.add(btnNewCombat);
        buttons.add(btnRest);
        buttons.add(btnWorkout);
        buttons.add(btnCardio);
        buttons.add(btnExitGame);

        playerName = new JPanel();
        playerName.add(lblPlayerName);

        enemyName = new JPanel();
        enemyName.add(lblEnemyName);

        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.linkSize(SwingConstants.VERTICAL, lblPlayerIcon, scrGameLog);
        layout.linkSize(SwingConstants.HORIZONTAL, lblPlayerIcon, playerName, enemyName);
        layout.linkSize(SwingConstants.VERTICAL, playerName, enemyName, gameProgress);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlayerIcon)
                        .addComponent(scrGameLog)
                        .addComponent(lblEnemyIcon))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(playerName)
                        .addComponent(gameProgress)
                        .addComponent(enemyName))
                .addComponent(buttons));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblPlayerIcon)
                        .addComponent(scrGameLog)
                        .addComponent(lblEnemyIcon))
                .addGroup(layout.createParallelGroup()
                        .addComponent(playerName)
                        .addComponent(gameProgress)
                        .addComponent(enemyName))
                .addComponent(buttons));
    }
}