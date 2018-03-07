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
    private int score; 
    private Game game;
    private int velocity; // to set the velocity of the enemy 
    
    public Enemy(int x, int y, int width, int height, int velocity, Game game) {
        super(x, y, width, height);
        this.game = game;
        direction = 1; 
        this.velocity = velocity; 
        score = (int) (Math.random() * 20) + 1;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public void tick() {
       setX(getX() + getVelocity() * getDirection()); // to move 1 depending in the direction
       if(getX() + 50 >= game.getWidth() || getX() <= -10){ // if it hits the borders, change direction and move down
           if(getVelocity() <= 8){ // set a limit for the velocity 
               setVelocity(getVelocity()+1);
           }
           setDirection(getDirection() * -1);
           setY(getY() + 4 + getVelocity());
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
