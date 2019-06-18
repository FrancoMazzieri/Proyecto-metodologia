/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Graphics.Assets;
import Math.Vector2D;
import input.KeyBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import main.windows;

/**
 *
 * @author Chory77
 */
public class Player extends MovingObject {

    private Vector2D heading;
    private Vector2D acceleration;
    private final double ACC = 0.2;
    private final double DELTAANGLE = 0.1;
    private boolean accelerating = false;

    public Player(Vector2D posicion, Vector2D velocity, double maxvel, BufferedImage textura) {
        super(posicion, velocity, maxvel, textura);
        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
    }

    @Override
    public void update() {
        if (KeyBoard.RIGHT) {
            angle += DELTAANGLE;
        }

        if (KeyBoard.LEFT) {
            angle -= DELTAANGLE;
        }

        if (KeyBoard.UP) {
            acceleration = heading.scale(ACC);
            accelerating = true;
        } else {
            if (velocity.getMagnitude() != 0)
                acceleration = (velocity.scale(-1).normalize()).scale(ACC / 2);
                accelerating = false;
            
            

        }
        velocity = velocity.add(acceleration);

        velocity = velocity.limit(maxvel);

        heading = heading.setDirection(angle - Math.PI / 2);

        posicion = posicion.add(velocity);

        if (posicion.getX() > windows.WIDTH) {
            posicion.setX(0);
        }
        if (posicion.getY() > windows.HEIGHT) {
            posicion.setY(0);
        }
        if (posicion.getX() < 0) {
            posicion.setX(windows.WIDTH);
        }
        if (posicion.getY() < 0) {
            posicion.setY(windows.HEIGHT);
        }

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + width / 2 +5, posicion.getY() + height / 2 +10);
        AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + 5, posicion.getY() + height / 2 + 10);
        at1.rotate(angle, -5, -10);
        at2.rotate(angle, width/2 -5, -10);
        if (accelerating) {
            g2d.drawImage(Assets.speed, at2, null);
            g2d.drawImage(Assets.speed, at1, null);
        }
        g2d.drawImage(Assets.speed, at2, null);

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angle, width / 2, height / 2);

        g2d.drawImage(Assets.player, at, null);

    }

}
