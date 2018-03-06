/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exambetterer;

import static java.awt.Color.blue;
import java.awt.Graphics;

/**
 *
 * @author angel
 */
public class Bullet extends Item{

    private int direction;
    private Game game;
    
    public Bullet(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
       setY(getY() - 5);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(blue);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
        
    }
}
