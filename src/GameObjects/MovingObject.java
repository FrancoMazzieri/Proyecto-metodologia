package GameObjects;

import Math.Vector2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class MovingObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    
    public MovingObject(Vector2D posicion,Vector2D velocity, BufferedImage textura) {
        super(posicion, textura);
        this.velocity = velocity;
        angle = 0;
    }

}
