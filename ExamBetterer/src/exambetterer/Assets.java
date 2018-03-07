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
    public static SoundClip Dead; //sound when you kill an enemy 
    public static SoundClip build; //when enemies appeared 
    public static SoundClip shoot; 

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/Nave.png");
        enemy = ImageLoader.loadImage("/images/Alien.png");
        Dead = new SoundClip("/Sounds/dead.wav");
        build = new SoundClip("/Sounds/build.wav");
        shoot = new SoundClip ("/Sounds/shoot.wav");
    }
}
