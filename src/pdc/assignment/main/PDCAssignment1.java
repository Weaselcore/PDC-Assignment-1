/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.main;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Weaselcore
 */
public class PDCAssignment1 {

    /**
     * Entry point to start a game.
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        Boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        File saveFolder = new File("src/pdc/assignment/saves/");
        
        while (isRunning) {
            
            System.out.println("KINGDOM FIGHTERS\n");
            System.out.println("[#1: New Game]");
            System.out.println("[#2: Load Game]");
            System.out.println("[#3: Exit]");
            System.out.print("Input: ");
            
            String result = input.nextLine();
            
            if ("1".equals(result)) {             
                GameSession gameSession = new GameSession();
                break;
            } else if ("2".equals(result)) {
                
                boolean hasSave;
                
                Scanner scanner = new Scanner(System.in);
                
                LinkedList saveFileList = new LinkedList();
                
                File[] listOfSaves = saveFolder.listFiles();
                
                if (listOfSaves.length == 0) {
                    System.out.println("There are no save files to show.");
                    hasSave = false;
                } else {
                    hasSave = true;
                }
                
                while (hasSave) {
                    int count = 1;

                    for (File file : listOfSaves) {
                        if (file.isFile()){
                            System.out.println("[#" + count +": " + file.getName() + "]");
                            saveFileList.add(file);
                            count += 1;
                        }
                    }

                    int saveChoice = scanner.nextInt();

                    if (saveChoice <= count) {
                        GameSession gameSession = new GameSession((File) saveFileList.get(saveChoice - 1));
                    } else {
                        System.out.println("Please input a valid save number");
                    }
                }
            } else if ("3".equals(result)){
                System.out.println("Thanks for playing!\n");
                System.exit(0);
                
            } else {
                System.out.println("Please input a proper option!");
            }
        }    
    }
}
