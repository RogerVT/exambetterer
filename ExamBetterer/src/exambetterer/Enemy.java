/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exambetterer;

import java.awt.Graphics;

/**
 *
 * @author angel
 */
public class Enemy extends Item{

    private int direction;
    private Game game;
    
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        direction = 1; 
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    @Override
    public void tick() {
       setX(getX() + 1 * getDirection()); // to move 1 depending in the direction
       if(getX() + 20 > game.getWidth()){ // if it hits the right border, change direction
           setDirection(-1);
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
