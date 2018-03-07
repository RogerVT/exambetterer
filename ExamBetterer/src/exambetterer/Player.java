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
public class Player extends Item{

    private Game game;
    private int iPixel;
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        iPixel = 5;
    }

    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().isLeft()) {
           setX(getX() - iPixel);
        }
        if (game.getKeyManager().isRight()) {
           setX(getX() + iPixel);
        }
        // reset x position if colision
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= 0) {
            setX(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
