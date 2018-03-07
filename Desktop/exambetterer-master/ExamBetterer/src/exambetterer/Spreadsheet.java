/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exambetterer;


import java.awt.image.BufferedImage;

/**
 *
 * @author angel
 */
public class Spreadsheet {
    private BufferedImage sheet; //to store the spritesheet
    
    /**
     * create a new spritesheet
     * @param sheet the <code>image</code> with the sprites
     */
    public Spreadsheet(BufferedImage sheet){
        this.sheet = sheet; 
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
