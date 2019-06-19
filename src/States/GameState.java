/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import GameObjects.MovingObject;
import GameObjects.Player;
import Graphics.Assets;
import Math.Vector2D;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Chory77
 */
public class GameState {

    private Player player;

    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();

    public GameState() {
        player = new Player(new Vector2D(400, 300), new Vector2D(), 7, Assets.player, this);
        movingObjects.add(player);
    }

    public void update() {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();

        }

    }

    public void draw(Graphics g) {
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);

        }

    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }

}
