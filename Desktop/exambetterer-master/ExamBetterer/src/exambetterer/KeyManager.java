/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exambetterer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author angel
 */
public class KeyManager implements KeyListener {
    
    //DEBEN SER PRIVDAAS
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean space;  //flag to space bar

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
           keys[e.getKeyCode()] = false;
        }else{
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
           keys[e.getKeyCode()] = true;
        }else{
            keys[e.getKeyCode()] = false;
        }
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        keys[KeyEvent.VK_SPACE] = false;
    }
}
