/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import GameObjects.Player;
import Graphics.Assets;
import Math.Vector2D;
import java.awt.Graphics;

/**
 *
 * @author Chory77
 */
public class GameState {

    private Player player;

    public GameState() {
        player = new Player(new Vector2D(400 , 300), new Vector2D(),7, Assets.player);
    } 

    
    
    public void update() {
        player.update();
    }

    public void draw(Graphics g) {
        player.draw(g);

    }

}