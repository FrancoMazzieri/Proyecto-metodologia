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

/**
 *
 * @author Chory77
 */
public class Player extends MovingObject {

    private Vector2D heading;
    private Vector2D acceleration;
    private final double ACC = 0.2;
    private final double DELTAANGLE = 0.1;
    
    public Player(Vector2D posicion, Vector2D velocity,double maxvel, BufferedImage textura) {
        super(posicion, velocity, maxvel, textura);
        heading = new Vector2D(0,1); 
        acceleration = new Vector2D();
    }

    @Override
    public void update() {
        if (KeyBoard.RIGHT) 
            angle +=DELTAANGLE;
        
        if (KeyBoard.LEFT) 
            angle -= DELTAANGLE;
        
         if (KeyBoard.UP) {
            acceleration = heading.scale(ACC);
        }
         else{
             if (velocity.getMagnitude()!=0)
                 acceleration = (velocity.scale(-1).normalize()).scale(ACC/2);
             
         }
        velocity= velocity.add(acceleration);
        
        velocity.limit(maxvel);
        
        heading = heading.setDirection(angle - Math.PI / 2);
        
        posicion = posicion.add(velocity);
        
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angle, Assets.player.getWidth() / 2, Assets.player.getHeight()/2);
        
        g2d.drawImage(Assets.player, at, null);
        

    }

}
