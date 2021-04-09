/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.pkg1;

import java.util.Scanner;

/**
 *
 * @author Weaselcore
 */
public class PDCAssignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        
        
        while (isRunning) {
            
            System.out.println("PLACEHOLDER SHOOTER\n");
            System.out.println("1: New Game");
            System.out.println("2: Load Game");
            System.out.println("3: Exit");
            System.out.print("Input: ");
            
            String result = input.nextLine();
            
            if ("1".equals(result)) {             
                GameSession newGameSession = new GameSession(true);
                newGameSession.playerTurn();
                newGameSession.enemyTurn();
                break;
                //gamecreator();
            } else if ("2".equals(result)) {
                GameSession newGameSession = new GameSession(false);   
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
