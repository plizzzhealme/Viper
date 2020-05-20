package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainPanel extends JPanel {
    public final JTable tblScores;
    public final JButton brnNewGame;
    public final DefaultTableModel model;
    final Image image = Toolkit.getDefaultToolkit().getImage("data/background.jpg");

    public MainPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBagLayout);

        model = new DefaultTableModel();

        model.addColumn("Nickname");
        model.addColumn("Hero");
        model.addColumn("Scores");

        tblScores = new JTable(model);
        tblScores.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tblScores);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints(scrollPane, gridBagConstraints);

        add(scrollPane);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        brnNewGame = new JButton(new ImageIcon("data/buttons/confirm.png"));
        brnNewGame.setPreferredSize(new Dimension(60, 60));
        brnNewGame.setToolTipText("New game");
        bottom.add(brnNewGame);
        bottom.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.weightx = 1;
        gridBagLayout.setConstraints(bottom, gridBagConstraints);
        add(bottom);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( image,
                getWidth() / 2 - image.getWidth(null) / 2,
                getHeight() / 2- image.getHeight(null) / 2,
                null,
                null );
    }
}