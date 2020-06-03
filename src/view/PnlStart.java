package view;

import javax.swing.*;
import java.awt.*;

public class PnlStart extends JPanel {
    /**
     * Displays game logo
     */
    private final JLabel lblLogo;

    /**
     * Displays game intro and rules
     */
    public final JTextArea txtInfo;

    /**
     * Displays the image with selected hero
     */
    public final JLabel lblHeroIcon;

    /**
     * Confirms hero selection and starts new game
     */
    public final JButton btnSelectHero;

    /**
     * List of available heroes to choose from
     */
    public final JComboBox<String> cmbHeroes;

    public PnlStart() {
        lblLogo = new JLabel(new ImageIcon("data/logo-image.png"));

        txtInfo = new JTextArea(1, 1);
        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);

        lblHeroIcon = new JLabel(new ImageIcon("resources/enemies/no-enemy.gif"));

        btnSelectHero = new JButton(new ImageIcon("resources/buttons/confirm.png"));
        btnSelectHero.setPreferredSize(new Dimension(32, 32));
        btnSelectHero.setToolTipText("Select hero");

        cmbHeroes = new JComboBox<>();

        addComponents();
    }

    private void addComponents() {
        JScrollPane scrInfo;
        JPanel buttonPanel;
        GroupLayout layout;

        scrInfo = new JScrollPane(txtInfo);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.add(btnSelectHero);

        layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.linkSize(SwingConstants.VERTICAL, lblLogo, scrInfo, lblHeroIcon);
        layout.linkSize(SwingConstants.HORIZONTAL, cmbHeroes, lblHeroIcon);
        layout.linkSize(SwingConstants.VERTICAL, buttonPanel, cmbHeroes);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(lblLogo)
                .addGroup(layout.createParallelGroup()
                        .addComponent(scrInfo)
                        .addComponent(buttonPanel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblHeroIcon)
                        .addComponent(cmbHeroes)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblLogo)
                        .addComponent(scrInfo)
                        .addComponent(lblHeroIcon))
                .addGroup(layout.createParallelGroup()
                        .addComponent(buttonPanel)
                        .addComponent(cmbHeroes)));
    }
}