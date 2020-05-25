package GameObjects;

import Graphics.Assets;
import Graphics.Sound;
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
    private Sound shoot;
    
    public Ufo(Vector2D posicion, Vector2D velocity, double maxvel, BufferedImage textura, ArrayList<Vector2D> path, GameState gameState) {
        super(posicion, velocity, maxvel, textura, gameState);
        this.path = path;
        index = 0;
        following = true;
        fireRate = new cronometer();
        fireRate.run(Constants.UFO_FIRE_RATE);
        shoot = new Sound(Assets.ufoShoot);
    }
    
    private Vector2D pathFollowing() {
        
        currentNode = path.get(index);
        double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
        if (distanceToNode < Constants.NODE_RADIUS) {
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
        
        pathFollowing = pathFollowing.scale(1 / Constants.UFO_MASS);
        
        velocity.add(pathFollowing);
        
        velocity = velocity.limit(maxvel);
        
        posicion = posicion.add(velocity);
        
        if (posicion.getX() > Constants.WIDTH || posicion.getY() > Constants.HEIGHT
                || posicion.getX() < Constants.WIDTH || posicion.getY() < Constants.HEIGHT) {
            Destroy();
        }

        //shoot
        if (!fireRate.isRunning()) {
            Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());
            
            toPlayer = toPlayer.normalize();
            
            double currentAngle = toPlayer.getAngle();
            
            currentAngle += Math.random() * Constants.UFO_ANGLE_RANGE - Constants.UFO_ANGLE_RANGE / 2;
            
            if (toPlayer.getX() < 0) {
                currentAngle = -currentAngle + Math.PI;
            }
            
           toPlayer = toPlayer.setDirection(currentAngle);
            
            Laser laser = new Laser(getCenter().add(toPlayer.scale(width)),
                    toPlayer, Constants.LASER_VEL, currentAngle + Math.PI / 2, Assets.redLaser, gameState
            );
            gameState.getMovingObjects().add(0, laser);
            
            fireRate.run(Constants.UFO_FIRE_RATE);
            
            shoot.play();
        }
        
        if (shoot.getFramePosition() > 8500){
            shoot.stop();
        }
        
        angle += 0.05;
        collidesWith();
        fireRate.update();
    }
    
    @Override
    protected void Destroy() {
        gameState.addScore(Constants.UFO_SCORE, posicion);
        super.Destroy();
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        
        at.rotate(angle, width / 2, height / 2);
        
        g2d.drawImage(textura, at, null);
        
    }
}
