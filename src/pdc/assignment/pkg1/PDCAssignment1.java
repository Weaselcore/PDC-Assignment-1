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
    public static void main(String[] args) {
        
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
                
                System.out.println("Please enter a name for your new brave adventurer:");
                String user_input = input.nextLine();
  
                
                GameSession newGameSession = new GameSession(false, user_input);
                break;
                //gamecreator();
            } else if ("2".equals(result)) {
                //loadGAme();
            } else {
                System.out.println("Thanks for playing!\n");
                System.exit(0);
            }
            
        }

        
    }
    
}
