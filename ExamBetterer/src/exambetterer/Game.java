package exambetterer;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private boolean gameOver;       //To stop the game
    private Player player;          // to use a player
    private ArrayList<Enemy> enemies; // To store an enemies colection
    private ArrayList<Bullet> bullets;  //to store bullets
    private KeyManager keyManager;  // to manage the keyboard
    private int VelocityEnemies; //to set the velocity of every enemy 
    
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        gameOver = false;
        keyManager = new KeyManager();
        VelocityEnemies = 1; 
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(0, getHeight() - 100, 100, 100, this);
         //Create the Array colection of enemies
         enemies = new ArrayList<Enemy>();
         //Adding enemies to the collection 
         //Aqui es donde se declaran el numero de enemigos y su posicion
         for(int i = 0; i < 7; i++){
             for(int j = 0; j < 5; j++){
                 int width_enemy = getWidth()/10;
                 Enemy enemy = new Enemy(i * width_enemy + 2,30 * j +5, 
                         width_enemy - 10, 25, VelocityEnemies, this); 
                 enemies.add(enemy);
             }
         }
         //Create the array list of bullets
         bullets = new ArrayList<Bullet>();
 
         display.getJframe().addKeyListener(keyManager);
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running && !gameOver) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }
    
    private void tick() {
        keyManager.tick();
        // avancing player with colision
        player.tick();
        //Moving the enemies
        Iterator itr = enemies.iterator();
        while(itr.hasNext()){
            
            Enemy enemy = (Enemy) itr.next();
            enemy.tick();
            
            //Check if colision for gameover
            //NO PUEDES UTILIZAR LA SINTAXIS EN EL TICK, PORQUE SE USA NEXT
            //PERO EN EL REDNER SI
            if(player.intersects(enemy)){
                gameOver = true;
            } 
            
            //Resets position
            if(enemy.getY() >= getHeight()){
                enemy.setX((int) (Math.random() * (getWidth() - 80)));
                enemy.setY(-(int)(Math.random()* 2 * getHeight()));
            }
        }
        
        //Check if a bullet must be add
        if(this.getKeyManager().space){
            bullets.add(new Bullet(this.player.getX() + player.getWidth()/2 - 10, this.player.getY(), 20, 20, 1, this));
        }
        //Update the bullet position
        itr = bullets.iterator();
        while(itr.hasNext()){
            
            Bullet bullet = (Bullet) itr.next();
            bullet.tick();
     
            Iterator itr2 = enemies.iterator();
            
            
            while(itr2.hasNext()){
                Enemy enemy = (Enemy) itr2.next();
                if(bullet.intersects(enemy)){
                    //if enemies is empty, add new set of enemies
                    enemies.remove(enemy);
                    if(enemies.isEmpty()){
                        for(int i = 0; i < 7; i++){
                            for(int j = 0; j < 5; j++){
                                int width_enemy = getWidth()/10;
                                enemies.add(new Enemy(i * width_enemy + 2, 30 * j +5, 
                                    width_enemy - 10, 25, enemy.getVelocity(),this)); 
                                enemies.add(enemy);
                            }
                        }
                    }
                    itr2 = enemies.iterator(); //update iterator
                    
                    bullets.remove(bullet);
                    //Update the iterators, to avoid problems with the iterator 
                    itr = bullets.iterator();
                }
            }
            //Check if the bullet is out of the screen 
            if(bullet.getY() <= 0){
                //Remove the bullet from the list
                 bullets.remove(bullet);
                //Update the iterators, to avoid problems with the iterator 
                itr = bullets.iterator();
            }
        }
        
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            
            Iterator itr = enemies.iterator();
            while(itr.hasNext()){
            ((Enemy) itr.next()).render(g);
            }
            
            itr = bullets.iterator();
            while(itr.hasNext()){
            ((Bullet) itr.next()).render(g);
            }
            
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}
