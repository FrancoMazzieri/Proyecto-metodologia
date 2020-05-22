/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Math.Vector2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Chory77
 */
public abstract class GameObject {
    protected BufferedImage textura;
    protected Vector2D posicion;

    public GameObject(Vector2D posicion, BufferedImage textura) {
        this.posicion = posicion;
        this.textura = textura;
    }
    
    public abstract void update();
    public abstract void draw(Graphics g);

    public Vector2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2D posicion) {
        this.posicion = posicion;
    }
    
    
}
