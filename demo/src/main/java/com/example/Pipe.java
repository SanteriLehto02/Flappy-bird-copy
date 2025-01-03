package com.example;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Pipe {
    int x;
    int y;
    int width = 50;
    int height = 400;
    boolean suunta;
    private static final String IMAGE_PATH = "C:\\flappy bird\\demo\\Flappy_Bird_assets by kosresetr55\\Game Objects\\pipe-green.png";
    public static final BufferedImage image = (BufferedImage) ImageLoader.loadImage(IMAGE_PATH);

    public Pipe() {
    }

    public Pipe(int x, int y,boolean suunta) {
        this.x = x;
        this.y = y;
        this.suunta = suunta;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Pipe x(int x) {
        setX(x);
        return this;
    }

    public Pipe y(int y) {
        setY(y);
        return this;
    }

    public void draw(Graphics2D g2) {
        if (this.suunta) {
            AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
            tx.scale(1, -1); 
    
            tx.translate(0, -image.getHeight());
    
            g2.drawImage(image, tx, null);
        } else {
            g2.drawImage(image, x, y, width, height, null);
        }
    }
}

