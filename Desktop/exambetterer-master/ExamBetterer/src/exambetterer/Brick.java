/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exambetterer;

/**
 *
 * @author antoniomejorado
 */

import java.awt.Color;
import java.awt.Graphics;


public class Brick extends Item{

    private Game game;
    private int hits; // for how many times you need to hit the brick 
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        hits = 3; 
    }

    public int getHits() {
        return hits;
    }
    

    public void setHits(int hits) {
        this.hits = hits;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) { 
        g.drawImage(Assets.brickanim[getHits()], getX(), getY(), getWidth(), getHeight(), null);
    }
}
