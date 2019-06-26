package GameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Math.Vector2D;
import States.GameState;
import java.util.ArrayList;

public abstract class MovingObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxvel;
    protected int width;
    protected int height;
    protected GameState gameState;

    public MovingObject(Vector2D posicion, Vector2D velocity, double maxvel, BufferedImage textura, GameState gameState) {
        super(posicion, textura);
        this.velocity = velocity;
        this.maxvel = maxvel;
        this.gameState = gameState;
        width = textura.getWidth();
        height = textura.getHeight();
        angle = 0;
    }

    protected void collidesWith() {
            ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();
        for (int i = 0; i < movingObjects.size(); i++) {
            MovingObject m = movingObjects.get(i);
            if (m.equals(this)) {
                continue;
            }

            double distance = m.getCenter().subtract(getCenter()).getMagnitude();
            if (distance < m.width / 2 + width / 2 && movingObjects.contains(this)) {
                objectCollision(m, this);
            }

        }

    }
    
    private void objectCollision(MovingObject a, MovingObject b){
        if(a instanceof Player && ((Player)a).isSpawning()) {
            return;
	}
	if(b instanceof Player && ((Player)b).isSpawning()) {
            return;
	}
        
        if(!(a instanceof Meteor && b instanceof Meteor)){
            gameState.playExplosion(getCenter());
            a.Destroy();
            b.Destroy();
        }
    }

    protected void Destroy() {
        gameState.getMovingObjects().remove(this);
    }

    protected Vector2D getCenter() {

        return new Vector2D(posicion.getX() + width / 2, posicion.getY() + height / 2);

    }

}
