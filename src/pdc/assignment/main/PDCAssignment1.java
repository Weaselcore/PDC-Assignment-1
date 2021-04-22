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
            System.out.println("[#3: Delete Save File]");           
            System.out.println("[#4: Exit]");
            System.out.print("Input: ");
            
            String result = input.nextLine();
            
            if ("1".equals(result)) {             
                GameSession gameSession = new GameSession();
                break;
            } else if ("2".equals(result)) {
                
                File fileResult = getSaveFile(saveFolder);
                GameSession gameSession = new GameSession(fileResult);

            } else if ("3".equals(result)){
                
                try {
                    File fileResult = getSaveFile(saveFolder);
                    if (!(fileResult == null)) {
                        if (fileResult.delete()) {
                            System.out.println("File has been deleted.");
                        } else {
                            System.out.println("File failed to be deleted.");
                        }
                    }
                }   catch(Exception e) {
                        System.out.println(e);
                }
            } else if ("4".equals(result)){
                System.out.println("Thanks for playing!\n");
                System.exit(0);    
            } else {
                System.out.println("Please input a proper option!");
            }
        } 
    } 
    
    // Used to retrieve list of saves for loading and deletion of files.
    public static File getSaveFile(File saveFolder) throws Exception {

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
            int count = 0;

            for (File file : listOfSaves) {
                if (file.isFile()){
                    System.out.println("[#" + (count + 1) +": " + file.getName() + "]");
                    saveFileList.add(file);
                    count += 1;
                }
            }

            int saveChoice = scanner.nextInt();
            
            if (saveChoice <= count) {
                return (File) saveFileList.get(saveChoice - 1);
            } else {
                throw new Exception("Please input a valid number.");
            }

        }
        return null;
    }
   
}
