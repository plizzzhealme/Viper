package view;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    // top
    public final JLabel lblHeroList;
    public final JComboBox<String> cmbHeroList;

    // center
    public final JTextArea txtInfo;

    // right
    public final JLabel lblHeroIcon;

    // bottom
    public final JButton btnSelectHero;

    public StartPanel() {
        // top
        lblHeroList = new JLabel("Choose a hero");
        cmbHeroList = new JComboBox<>();

        // center
        txtInfo = new JTextArea();

        // right
        lblHeroIcon = new JLabel();

        // bottom
        btnSelectHero = new JButton(new ImageIcon("data/buttons/confirm.png"));

        addComponents();
    }

    private void addComponents() {

        setLayout(new BorderLayout());

        // top
       // top = new JPanel();
      //  top.add(lblHeroList);
      //  top.add(cmbHeroList);
      //  add(top, BorderLayout.PAGE_START);

        // center
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(new JScrollPane(txtInfo), BorderLayout.CENTER);
      //  add(center, BorderLayout.CENTER);

        // right
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
      //  JPanel rightTop = new JPanel();
      //  rightTop.add(cmbHeroList);
        JPanel cmb = new JPanel();
        cmb.add(cmbHeroList);


        JPanel icon = new JPanel();
        icon.setBorder(BorderFactory.createEtchedBorder());
        icon.add(lblHeroIcon);
        right.add(icon);
        right.add(cmb);
        btnSelectHero.setPreferredSize(new Dimension(32, 32));
        btnSelectHero.setToolTipText("Select hero");
        right.add(btnSelectHero);

        add(right, BorderLayout.LINE_END);

        // bottom

      //  bottom = new JPanel();
      //  bottom.setLayout(new FlowLayout());
      //  bottom.add(btnSelectHero);
       // add(bottom, BorderLayout.PAGE_END);
    }
}