/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Graphics.Assets;
import Graphics.Sound;
import Math.Vector2D;
import States.GameState;
import input2.KeyBoard2;
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
    private cronometer fireRate;
    private boolean spawning, visible;
    private cronometer spawnTime, flickerTime;

    private Sound shoot, loose;
     
    public Player(Vector2D posicion, Vector2D velocity, double maxvel, BufferedImage textura, GameState gameState) {
        super(posicion, velocity, maxvel, textura, gameState);

        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
        fireRate = new cronometer();
        spawnTime = new cronometer();
        flickerTime = new cronometer();
        shoot = new Sound(Assets.playerShoot);
        loose = new Sound(Assets.playerLoose);
    }

    @Override
    public void update() {

        if (!spawnTime.isRunning()) {
            spawning = false;
            visible = true;
        }

        if (spawning) {
            if (!flickerTime.isRunning()) {
                flickerTime.run(Constants.FLICKER_TIME);
                visible = !visible;
            }
        }

        if (KeyBoard2.SHOOT && !fireRate.isRunning() && !spawning) {
            gameState.getMovingObjects().add(0, new Laser(getCenter().add(heading.scale(width)),
                    heading,
                    Constants.LASER_VEL,
                    angle,
                    Assets.redLaser,
                    gameState));
            fireRate.run(Constants.FIRERATE);
            shoot.play();
        }
        
        if(shoot.getFramePosition() > 8500){
            shoot.stop();
        }
        
        if (KeyBoard2.RIGHT) {
            angle += Constants.DELTAANGLE;
        }

        if (KeyBoard2.LEFT) {
            angle -= Constants.DELTAANGLE;
        }

        if (KeyBoard2.UP) {
            acceleration = heading.scale(Constants.ACC);
            accelerating = true;
        } else {
            if (velocity.getMagnitude() != 0) {
                acceleration = (velocity.scale(-1).normalize()).scale(Constants.ACC / 2);
            }
            accelerating = false;

        }
        velocity = velocity.add(acceleration);

        velocity = velocity.limit(maxvel);

        heading = heading.setDirection(angle - Math.PI / 2);

        posicion = posicion.add(velocity);

        if (posicion.getX() > Constants.WIDTH) {
            posicion.setX(0);
        }
        if (posicion.getY() > Constants.WIDTH) {
            posicion.setY(0);
        }
        if (posicion.getX() < 0) {
            posicion.setX(Constants.HEIGHT);
        }
        if (posicion.getY() < 0) {
            posicion.setY(Constants.HEIGHT);
        }
        fireRate.update();
        spawnTime.update();
        flickerTime.update();
        collidesWith();
    }

    @Override
    public void Destroy() {
        spawning = true;
        spawnTime.run(Constants.SPAWNING_TIME);
        loose.play();
        resetValues();
        gameState.subtractLife();
    }

    private void resetValues() {
        angle = 0;
        velocity = new Vector2D();
        posicion = new Vector2D(Constants.WIDTH / 2 - Assets.player.getWidth() / 2, Constants.HEIGHT / 2 - Assets.player.getHeight() / 2);
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + width / 2 + 5, posicion.getY() + height / 2 + 10);
        AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX() + 5, posicion.getY() + height / 2 + 10);
        at1.rotate(angle, -5, -10);
        at2.rotate(angle, width / 2 - 5, -10);
        if (accelerating) {
            g2d.drawImage(Assets.speed, at2, null);
            g2d.drawImage(Assets.speed, at1, null);
        }

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angle, width / 2, height / 2);

        g2d.drawImage(textura, at, null);

    }

    public boolean isSpawning() {
        return spawning;
    }
}
