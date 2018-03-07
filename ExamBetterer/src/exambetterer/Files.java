/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exambetterer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author angel
 */
public class Files { //i dont know whats happening in this file
    public static void saveFile(Game game){
        //define objects
        PrintWriter printWriter; 
        try{
             //Creating file object
            printWriter = new PrintWriter(new FileWriter("data.txt"));
            //Writing the game
            printWriter.println("" + game.getPlayer().getX() + "," + 
                    game.getPlayer().getY());
            printWriter.println("" + game.getEnemies().size());
            for(Enemy enemy : game.getEnemies()){
                printWriter.println("" + enemy.getX() + "," + enemy.getY() +
                        "," + enemy.getHeight() + ",");
            }
            printWriter.close();
            
        } catch (IOException ioe) {
           System.out.println("Se lleno el disco duro"); 
        }
    }
       public static void loadFile(Game game){
       BufferedReader bufferedReader;
       try{
           System.out.println("Entra al archivo");
           bufferedReader = new BufferedReader(new FileReader("data.txt"));
           //to get first line
           String line = bufferedReader.readLine();
           String[] tokens = line.split(",");
           game.getPlayer().setX(Integer.parseInt(tokens[0]));
           game.getPlayer().setY(Integer.parseInt(tokens[1]));
           //Getting POINTS and CRASH
           line = bufferedReader.readLine();
           tokens = line.split(",");
           //get # enemies
           int enemies = Integer.parseInt(bufferedReader.readLine());
           //clear enemies
           game.getEnemies().clear();
           //adding enemies
           for(int i = 0; i < enemies; i++){
                //getting next line
                line = bufferedReader.readLine();
                tokens = line.split(",");
                int X = (Integer.parseInt(tokens[0]));
                int Y = (Integer.parseInt(tokens[1]));
                int Height = (Integer.parseInt(tokens[2]));
                int move = (Integer.parseInt(tokens[3]));
                
                Enemy enemy = new Enemy(X, Y, 100, Height, game);
                
                game.getEnemies().add(enemy);
           }
           
       } catch(IOException ioe){
           System.out.println("Juego no ha sido guardado " + ioe.toString());
       }
       
   }
}
