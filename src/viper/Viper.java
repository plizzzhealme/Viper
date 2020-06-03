package viper;

import controller.StartController;

public class Viper {

    public static void main(String[] args) {
        new Viper().run();
    }

    public void run() {
        new StartController();
    }
}