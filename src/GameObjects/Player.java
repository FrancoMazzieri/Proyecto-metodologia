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

    public Player(Vector2D posicion, Vector2D velocity, BufferedImage textura) {
        super(posicion, velocity, textura);
        heading = new Vector2D(0,1); 
    }

    @Override
    public void update() {
        if (KeyBoard.RIGHT) {
            angle += Math.PI / 20;
        }
        if (KeyBoard.LEFT) {
            angle -= Math.PI / 20;
        }

        heading = heading.setDirection(angle - Math.PI / 2);

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
        at.rotate(angle, Assets.player.getWidth() / 2, Assets.player.getHeight()/2);
        
        g2d.drawImage(Assets.player, at, null);
        

    }

}
