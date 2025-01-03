package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Score {
    int points = 0;
    String pointsString = "0";
    private static final String zeroImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\0.png";
    private static final String oneImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\1.png";
    private static final String twoImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\2.png";
    private static final String threeImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\3.png";
    private static final String fourImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\4.png";
    private static final String fiveImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\5.png";
    private static final String sixImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\6.png";
    private static final String sevenImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\7.png";
    private static final String eightImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\8.png";
    private static final String nineImage = "demo\\Flappy_Bird_assets by kosresetr55\\UI\\Numbers\\9.png";
    public static final BufferedImage zero = (BufferedImage) ImageLoader.loadImage(zeroImage);
    public static final BufferedImage one = (BufferedImage) ImageLoader.loadImage(oneImage);
    public static final BufferedImage two = (BufferedImage) ImageLoader.loadImage(twoImage);
    public static final BufferedImage three = (BufferedImage) ImageLoader.loadImage(threeImage);
    public static final BufferedImage four = (BufferedImage) ImageLoader.loadImage(fourImage);
    public static final BufferedImage five = (BufferedImage) ImageLoader.loadImage(fiveImage);
    public static final BufferedImage six = (BufferedImage) ImageLoader.loadImage(sixImage);
    public static final BufferedImage seven = (BufferedImage) ImageLoader.loadImage(sevenImage);
    public static final BufferedImage eight = (BufferedImage) ImageLoader.loadImage(eightImage);
    public static final BufferedImage nine = (BufferedImage) ImageLoader.loadImage(nineImage);


    public Score() {
        // TODO Auto-generated method stub
    }

    public void setPoints(int points){
        this.points = points;
        this.pointsString = Integer.toString(this.points);
    }
    public void draw(Graphics2D g2) {
        int i = 0;
        int xPosition = 432 / 2;
        int yPosition = 100;  
        while (i < pointsString.length()) { 

            char digit = pointsString.charAt(i);
            BufferedImage digitImage = null;

            switch (digit) {
                case '0' -> digitImage = zero;
                case '1' -> digitImage = one;
                case '2' -> digitImage = two;
                case '3' -> digitImage = three;
                case '4' -> digitImage = four;
                case '5' -> digitImage = five;
                case '6' -> digitImage = six;
                case '7' -> digitImage = seven;
                case '8' -> digitImage = eight;
                case '9' -> digitImage = nine;
            }

            g2.drawImage(digitImage, xPosition, yPosition, 40, 40, null);

            xPosition -= 20;
            i++;
        }
            
        
    }
}
