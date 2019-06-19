/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Graphics.Assets;
import Math.Vector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Franco
 */
public class Laser extends MovingObject {

    public Laser(Vector2D posicion, Vector2D velocity, double maxvel, double angle, BufferedImage textura) {
        super(posicion, velocity, maxvel, textura);
        this.angle = angle;
        this.velocity = velocity.scale(maxvel);
    }

    @Override
    public void update() {
        posicion = posicion.add(velocity);
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion.getX() - width / 2, posicion.getY());

        at.rotate(angle, width / 2, 0);

        g2d.drawImage(textura, at, null);

    }

}
