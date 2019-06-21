/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GameObjects.constans;
import Graphics.Assets;
import States.GameState;
import input.KeyBoard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class windows extends JFrame implements Runnable {

    public static final int WIDTH = 800, HEIGHT = 600;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private final int fps = 60;
    private double targetTime = 1000000000 / fps;
    private double delta = 0;
    private int averageFps = fps;

    private GameState gameState;
    private KeyBoard keyBoard;

    public windows() {
        setTitle("JavaApplication32");
        setSize(constans.WIDTH,constans.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
       
        
        canvas = new Canvas();
        keyBoard = new KeyBoard();
        
        canvas.setPreferredSize(new Dimension(constans.WIDTH,constans.HEIGHT));
        canvas.setMaximumSize(new Dimension(constans.WIDTH,constans.HEIGHT));
        canvas.setMinimumSize(new Dimension(constans.WIDTH,constans.HEIGHT));
        canvas.setFocusable(true);
        add(canvas);
        canvas.addKeyListener(keyBoard);
        setVisible(true);
    }

    public static void main(String[] args) {
        new windows().start();
        System.out.println("ss");
    }

    private void update() {
        keyBoard.update();
        gameState.update();

    }

    private void draw() {
        bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //----------------------------------

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, constans.WIDTH,constans.HEIGHT);
        gameState.draw(g);
        g.drawString(""+averageFps, 10, 20);
        //----------------------------------
        g.dispose();
        bs.show();
    }

    private void init() {

        Assets.init();
        gameState = new GameState();

    }

    @Override
    public void run() {

        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / targetTime;
            time += (now - lastTime);
            lastTime = now;
            
            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
            }
            if (time >= 1000000000) {
                averageFps = frames;
               
                frames = 0;
                time = 0;
            }

        }
        stop();
    }

    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
                   e.printStackTrace();
        }
    }
}
