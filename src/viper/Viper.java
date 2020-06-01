package viper;

import controller.StartController;

import javax.swing.*;

public class Viper {

    public static void main(String[] args) {
        StartController startController;
        JFrame frame;

        startController = new StartController();
        frame = new JFrame();

        frame.setSize(750, 375);
        frame.setIconImage(new ImageIcon("data/logo-icon.png").getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(startController.getCards());
        frame.setVisible(true);
    }
}