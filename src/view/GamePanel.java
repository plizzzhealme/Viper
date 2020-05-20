package view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // left
    public final JLabel lblPlayerName;
    public final JLabel lblPlayerIcon;

    // center
    private final JScrollPane scrGameLog;
    public final JTextArea txtGameLog;
    public final JLabel lblGameProgress;

    // right
    public final JLabel lblEnemyName;
    public final JLabel lblEnemyIcon;

    // bottom
    public final JButton btnNewCombat;
    public final JButton btnRest;
    public final JButton btnWorkout;
    public final JButton btnCardio;
    public final JButton btnExitGame;

    public GamePanel() {
        Dimension buttonSize;

        // left
        lblPlayerName = new JLabel();
        lblPlayerIcon = new JLabel();

        // center
        txtGameLog = new JTextArea(1, 1);
        scrGameLog = new JScrollPane(txtGameLog);
        lblGameProgress = new JLabel("<html><p>Game progress: <b>5</b></p></html>");

        // right
        lblEnemyName = new JLabel("enemy");
        lblEnemyIcon = new JLabel();

        // bottom
        buttonSize = new Dimension(32, 32);

        btnNewCombat = new JButton(new ImageIcon("data/buttons/start-combat.png"));
        btnNewCombat.setPreferredSize(buttonSize);
        btnNewCombat.setToolTipText("New combat");

        btnRest = new JButton(new ImageIcon("data/buttons/rest.png"));
        btnRest.setPreferredSize(buttonSize);
        btnRest.setToolTipText("Rest");

        btnWorkout = new JButton(new ImageIcon("data/buttons/workout.png"));
        btnWorkout.setPreferredSize(buttonSize);
        btnWorkout.setToolTipText("Workout");

        btnCardio = new JButton(new ImageIcon("data/buttons/cardio.png"));
        btnCardio.setPreferredSize(buttonSize);
        btnCardio.setToolTipText("Cardio");

        btnExitGame = new JButton(new ImageIcon("data/buttons/exit.png"));
        btnExitGame.setPreferredSize(buttonSize);
        btnExitGame.setToolTipText("Exit");

        addComponents();
    }

    private void addComponents() {
        GroupLayout layout;
        JPanel playerName;
        JPanel gameProgress;
        JPanel enemyName;
        JPanel buttons;

        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        buttons = new JPanel();
        buttons.add(btnNewCombat);
        buttons.add(btnRest);
        buttons.add(btnWorkout);
        buttons.add(btnCardio);
        buttons.add(btnExitGame);

        gameProgress = new JPanel();
        gameProgress.add(lblGameProgress);

        playerName = new JPanel();
        playerName.add(lblPlayerName);

        enemyName = new JPanel();
        enemyName.add(lblEnemyName);

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

        layout.linkSize(SwingConstants.VERTICAL, lblPlayerIcon, scrGameLog);
        layout.linkSize(SwingConstants.HORIZONTAL, lblPlayerIcon, playerName, enemyName);
    }
}