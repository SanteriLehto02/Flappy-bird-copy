package com.example;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("flappy bird main!");

        System.out.println("Typing game");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Typing game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        gamePanel.startGameThread();
    }
}