/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.main;

import java.util.Scanner;

/**
 *
 * @author Weaselcore
 */
public class PDCAssignment1 {

    /**
     * Entry point to start a game.
     */
    public static void main(String[] args) throws Exception {
        
        Boolean isRunning = true;
        GameSession gameSession;
        Scanner input = new Scanner(System.in);
        
        
        while (isRunning) {
            
            System.out.println("Kingdom Fighters\n");
            System.out.println("1: New Game");
            System.out.println("2: Load Game");
            System.out.println("3: Exit");
            System.out.print("Input: ");
            
            String result = input.nextLine();
            
            if ("1".equals(result)) {             
                gameSession = new GameSession(true);
                break;
                //gamecreator();
            } else if ("2".equals(result)) {
                gameSession = new GameSession(false);   
                break;
                // Display save files in resource package.
                // Create menu for 3 saves.
                // Retrieve data and create old player object from file.
            } else {
                System.out.println("Thanks for playing!\n");
                System.exit(0);
            }       
        }    
    }
}
