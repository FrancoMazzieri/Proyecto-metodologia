package GameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import Math.Vector2D;
import States.GameState;

public abstract class MovingObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxvel;
    protected int width;
    protected int height;
    protected GameState gameState;
    
    public MovingObject(Vector2D posicion,Vector2D velocity,double maxvel, BufferedImage textura,GameState gameState) {
        super(posicion, textura);
        this.velocity = velocity;
        this.maxvel = maxvel;
        this.gameState = gameState;
        width = textura.getWidth();
        height = textura.getHeight();
        angle = 0;
    }

}
