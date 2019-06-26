/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Graphics.Assets;
import Math.Vector2D;
import States.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Franco
 */
public class Ufo extends MovingObject {

    private ArrayList<Vector2D> path;
    private Vector2D currentNode;
    private int index;
    private boolean following;
    private cronometer fireRate;

    public Ufo(Vector2D posicion, Vector2D velocity, double maxvel, BufferedImage textura, ArrayList<Vector2D> path, GameState gameState) {
        super(posicion, velocity, maxvel, textura, gameState);
        this.path = path;
        index = 0;
        following = true;
        fireRate = new cronometer();
        fireRate.run(constans.UFO_FIRE_RATE);

    }

    private Vector2D pathFollowing() {

        currentNode = path.get(index);
        double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
        if (distanceToNode < constans.NODE_RADIUS) {
            index++;
            if (index >= path.size()) {
                following = false;
            }

        }
        return seekForce(currentNode);
    }

    private Vector2D seekForce(Vector2D target) {
        Vector2D desiredVelocity = target.subtract(getCenter());
        desiredVelocity = desiredVelocity.normalize().scale(maxvel);
        return desiredVelocity.subtract(velocity);
    }

    @Override
    public void update() {
        Vector2D pathFollowing;
        if (following) {
            pathFollowing = pathFollowing();
        } else {
            pathFollowing = new Vector2D();
        }

        pathFollowing = pathFollowing.scale(1 / constans.UFO_MASS);

        velocity.add(pathFollowing);

        velocity = velocity.limit(maxvel);

        posicion = posicion.add(velocity);

        if (posicion.getX() > constans.WIDTH || posicion.getY() > constans.HEIGHT
                || posicion.getX() < 0 || posicion.getY() < 0) {
            Destroy();
        }

        //shoot
        if (!fireRate.isRunning()) {
            Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());

            toPlayer = toPlayer.normalize();

            double currentAngle = toPlayer.getAngle();

            double newAngle = Math.random() * (Math.PI) - Math.PI / 2 + currentAngle;

            toPlayer.setDirection(newAngle);

            Laser laser = new Laser(getCenter().add(toPlayer.scale(width)),
                    toPlayer, constans.LASER_VEL, newAngle + Math.PI / 2, Assets.redLaser, gameState
            );
            gameState.getMovingObjects().add(0, laser);
            
            fireRate.run(constans.UFO_FIRE_RATE);
        }
        angle += 0.05;

        collidesWith();
        fireRate.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());

        at.rotate(angle, width / 2, height / 2);

        g2d.drawImage(textura, at, null);

    }

}
