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
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy;     // to store the player image
    public static BufferedImage bricks; 
    public static BufferedImage brickanim[];    //to store each sprite that is gonna be display
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/forest.png");
        player = ImageLoader.loadImage("/images/theylive.png");
        enemy = ImageLoader.loadImage("/images/Enemy.png");
        bricks = ImageLoader.loadImage("/images/bricks.png"); //spritessheet5
        Spreadsheet spritesheet = new Spreadsheet(bricks);
        brickanim = new BufferedImage[4];
        //croping the pictures from sheet into the array
        for(int i = 0; i < 4; i++){
            brickanim[i] = spritesheet.crop(i*100,0, 100, 50);//depends of the size of your character
        }
        
        
    }
}
