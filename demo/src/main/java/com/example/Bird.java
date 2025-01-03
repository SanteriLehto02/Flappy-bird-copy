package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bird {

    final  private SoundPlayer jumpSound;
    private SoundPlayer hitSound;

    int x;
    int y;
    int width = 30;
    int height = 30;
    int verticalSpeed = 0;
    int speed = 1;
    int num = 0;
    int jumpNum = 0;
    int stateImage = 0;
    boolean jumped = false;
    private static final String UpFlap = "demo\\Flappy_Bird_assets by kosresetr55\\Game Objects\\yellowbird-upflap.png";
    private static final String DownFlap = "demo\\Flappy_Bird_assets by kosresetr55\\Game Objects\\yellowbird-downflap.png";
    private static final String MidFlap = "demo\\Flappy_Bird_assets by kosresetr55\\Game Objects\\yellowbird-midflap.png";
    public static final BufferedImage UpFlapImage = (BufferedImage) ImageLoader.loadImage(UpFlap);
    public static final BufferedImage DownFlapImage = (BufferedImage) ImageLoader.loadImage(DownFlap);
    public static final BufferedImage MidFlapImage = (BufferedImage) ImageLoader.loadImage(MidFlap);
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;

        jumpSound = new SoundPlayer("demo\\Flappy_Bird_assets by kosresetr55\\Sound Efects//wing.wav");
    }

    public void jump(){
        if (!jumped) {
            
        
        this.verticalSpeed = -4;
        jumpSound.play();
        jumped = true;
        }
    }

    public void movement(){

        if (verticalSpeed > 0) {
            jumped = false;
        }

        this.y = this.y + this.verticalSpeed;
        if (this.num == 4) {
            this.verticalSpeed += this.speed;
            if (stateImage < 3) {
                this.stateImage += 1;
            }else{
                this.stateImage = 0;
            }
            
           
            this.num = 0;
        }
        
        this.num += 1;
    }

    public void draw(Graphics2D g2){
        /* 
        g2.setColor(Color.GREEN);
        g2.fillRect(this.x, this.y, this.width, this.height);
        */

        switch (stateImage) {
            case 0 -> g2.drawImage(UpFlapImage, this.x, this.y, this.width, this.height, null);
            case 1 -> g2.drawImage(MidFlapImage, this.x, this.y, this.width, this.height, null);
            default -> g2.drawImage(DownFlapImage, this.x, this.y, this.width, this.height, null);
        }
    }

}