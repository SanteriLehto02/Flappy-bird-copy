package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private SoundPlayer hitSound;
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 9;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int points = -1;
    int fps = 60;
    int speed = 2;
    List<List<Pipe>> pipes;
    Score score;
    Pipe pipe = new Pipe(-40,0,false);
    Pipe pipeReverse = new Pipe(pipe.x,0,true);
    Pipe pipeFix = new Pipe(pipe.x, -300,false);
    List<Pipe> pipes1;
    Pipe pipe2  = new Pipe(300,0,false);
    Pipe pipeReverse2 =  new Pipe(pipe2.x,0,true);
    Pipe pipeFix2 = new Pipe(pipe2.x, -800,false);
    List<Pipe> pipes2;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    Bird bird = new Bird(100,100);
    boolean gameState = true;
    boolean gameOnGoing = false;
    Image base = ImageLoader.loadImage("demo//Flappy_Bird_assets by kosresetr55\\Game Objects\\base.png");
    Image background = ImageLoader.loadImage("demo\\Flappy_Bird_assets by kosresetr55\\Game Objects\\background-day.png");
    Image startGameImage = ImageLoader.loadImage("demo\\Flappy_Bird_assets by kosresetr55\\UI\\message.png");
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        hitSound = new SoundPlayer("demo\\Flappy_Bird_assets by kosresetr55\\Sound Efects//die.wav");
        pipes1 = new ArrayList<>();
        pipes2 = new ArrayList<>();
        pipes = new ArrayList<>();
        pipes1.add(pipe);
        pipes1.add(pipeReverse);
        pipes1.add(pipeFix);
        pipes.add(pipes1);
        pipes2.add(pipe2);
        pipes2.add(pipeReverse2);
        pipes2.add(pipeFix2);
        pipes.add(pipes2);

        score = new Score();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void handleKeyPress(KeyEvent e) {
        
       
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        
    }
    public void update() {
        if (gameOnGoing) {
            
            for (Pipe p : pipes.get(0)) {
                p.x -= speed;
            }
            for (Pipe p : pipes.get(1)) {
                p.x -= speed;
            }
            
            if (bird.x == pipes.get(0).get(0).x-20 || bird.x <  pipes.get(0).get(0).x + pipes.get(0).get(0).width && bird.x > pipes.get(0).get(0).x) {
                if (bird.y > pipes.get(0).get(0).y-30 || bird.y < pipes.get(0).get(0).y - 130) {
                    gameOnGoing = false;
                    hitSound.play();
                }
            }
            if (bird.x == pipes.get(1).get(0).x-20 || bird.x <  pipes.get(1).get(0).x + pipes.get(1).get(0).width && bird.x > pipes.get(1).get(0).x) {
                if (bird.y > pipes.get(1).get(0).y-30 || bird.y < pipes.get(1).get(0).y - 130) {
                    gameOnGoing = false;
                    hitSound.play();
                }
            }
            if (bird.y >= 620) {
                gameOnGoing = false;
                hitSound.play();
            }
            if (gameState && bird.x > pipes.get(0).get(0).x+50 ) {
                gameState = false;
                points += 1;
                score.setPoints(points);
            }
            
            if (gameState && bird.x > pipes.get(1).get(0).x+50 ) {
                gameState = false;
                points += 1;
                
                score.setPoints(points);
            }
            if (pipes.get(0).get(0).x < -50 ) {
                 int randomNum = (int) (Math.random() * 301) + 200;
                
                pipes.get(0).get(0).x = 500;
                pipes.get(0).get(1).x = 500;
                pipes.get(0).get(2).x = 500;
                pipes.get(0).get(0).y = randomNum;
                pipes.get(0).get(1).y = randomNum - 450;
                pipes.get(0).get(2).y = randomNum - 800;
                
                gameState = true;
            }
            if (pipes.get(1).get(0).x < -50 ) {
                int randomNum = (int) (Math.random() * 301) + 200;
               
               pipes.get(1).get(0).x = 500;
               pipes.get(1).get(1).x = 500;
               pipes.get(1).get(2).x = 500;
               pipes.get(1).get(0).y = randomNum;
               pipes.get(1).get(1).y = randomNum - 450;
               pipes.get(1).get(2).y = randomNum - 800;
               gameState = true;
           }
            if (keyH.spacePressed) {
                bird.jump();
            }
            bird.movement();
        }else if(!gameOnGoing){ 
            if (keyH.spacePressed) {
                
                gameOnGoing = true;
                bird.y = 100;
                int randomNum = (int) (Math.random() * 301) + 200;

                pipes.get(0).get(0).x = 500;
                pipes.get(0).get(1).x = 500;
                pipes.get(0).get(0).y = randomNum;
                pipes.get(0).get(1).y = randomNum - 450;

                randomNum = (int) (Math.random() * 301) + 200;

                pipes.get(1).get(0).x = 500+260;
                pipes.get(1).get(1).x = 500+260;
                pipes.get(1).get(0).y = randomNum;
                pipes.get(1).get(1).y = randomNum - 450;
                
                points = 0;
                score.setPoints(points);
                bird.jump();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, 500, 800, null);
        if (gameOnGoing) {
            for(Pipe p : pipes.get(0)){
                p.draw(g2);
            }
            
            for(Pipe p : pipes.get(1)){
                p.draw(g2);
            }
            
        g2.drawImage(base, 0, 620, 600, 200, null);
        bird.draw(g2);

    
        
        
        }else{
            g2.setColor(Color.red);
            g2.drawString("Press space to start the game!", screenWidth/2-100 , 100);
            g2.drawImage(startGameImage, 65, 30, 300, 600, null);
        }
        score.draw(g2);
    }
}


