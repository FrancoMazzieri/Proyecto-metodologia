package GameObjects;

import Graphics.Assets;
import Math.Vector2D;
import States.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author emalu
 */
public class Meteor extends MovingObject {
    
    private Size size;
    private Image texture;

    public Meteor(Vector2D posicion, Vector2D velocity, double maxvel, BufferedImage textura, GameState gameState, Size size) {
        super(posicion, velocity, maxvel, textura, gameState);
    this.size = size;
   
    }

    @Override
    public void update() {
        posicion = posicion.add(velocity);
        
        if (posicion.getX() > constans.WIDTH) {
            posicion.setX(0);
        }
        if (posicion.getY() > constans.WIDTH) {
            posicion.setY(0);
        }
        if (posicion.getX() < 0) {
            posicion.setX(constans.HEIGHT);
        }
        if (posicion.getY() < 0) {
            posicion.setY(constans.HEIGHT);
            
            angle += constans.DELTAANGLE/2;
            
            
            
        }
        
    }

    @Override
    public void draw(Graphics g) {
        
        
        Graphics2D g2d = (Graphics2D)g;

          at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angle, width / 2, height / 2);

       g2d.drawImage(texture, at, null);
    }
    
    public Size getSize(){
return size;
}
}