package viper;

import controller.StartController;
import model.Boxer;
import model.Hero;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Viper {
    private static final int HEROES_NUMBER = 2;
    private static final int ENEMIES_NUMBER = 4;
    private static final List<ImageIcon> heroIcons;
    private static final List<ImageIcon> enemyIcons;
    private static final List<Hero> heroes;
    private static final List<Boxer> enemies;
    private static final List<String> intro;

    static {
        List<String> temp;

        heroIcons = new ArrayList<>(HEROES_NUMBER);
        heroes = new ArrayList<>(HEROES_NUMBER);
        temp = readFromFile("data/heroes/heroes.txt");

        for (int i = 0; i < HEROES_NUMBER; i++) {
            String[] hero = temp.get(i).split(" ");
            heroes.add(new Hero(hero[0], Integer.parseInt(hero[1]), Integer.parseInt(hero[2])));
            heroIcons.add(new ImageIcon("data/heroes/hero-" + i + ".gif"));
        }

        enemies = new ArrayList<>(ENEMIES_NUMBER);
        enemyIcons = new ArrayList<>(ENEMIES_NUMBER);
        temp = readFromFile("data/enemies/enemies.txt");

        for (int i = 0; i < ENEMIES_NUMBER; i++) {
            String[] enemy = temp.get(i).split(" ");
            enemies.add(new Boxer(enemy[0], Integer.parseInt(enemy[1]), Integer.parseInt(enemy[2])));
            enemyIcons.add(new ImageIcon("data/enemies/enemy-" + i + ".gif"));
        }

        intro = readFromFile("data/intro.txt");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(666   , 389);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new StartController(frame, intro, heroes, heroIcons, enemies, enemyIcons).run();
    }

    public static List<String> readFromFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void writeToFile(String data, String path, boolean append) {
        try (FileWriter writer = new FileWriter(path, append)) {
            writer.write(data);
           // writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}