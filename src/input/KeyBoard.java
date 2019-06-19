/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Franco
 */
public class KeyBoard implements KeyListener {

    private boolean[] keys = new boolean[256];

    public static boolean UP, LEFT, RIGHT, SHOOT;

    public KeyBoard() {
        UP = false;
        LEFT = false;
        RIGHT = false;
        SHOOT = false;
    }

    public void update() {
        UP = keys[KeyEvent.VK_UP];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        SHOOT = keys[KeyEvent.VK_P]; 
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }
}
