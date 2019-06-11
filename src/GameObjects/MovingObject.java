package GameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Math.Vector2D;

public abstract class MovingObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxvel;
    public MovingObject(Vector2D posicion,Vector2D velocity,double maxvel, BufferedImage textura) {
        super(posicion, textura);
        this.velocity = velocity;
        this.maxvel = maxvel;
        angle = 0;
    }

}
