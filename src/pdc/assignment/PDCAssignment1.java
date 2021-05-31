/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment;

import pdc.assignment.gameclasses.GameSession;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import pdc.assignment.gui.controller.GameController;
import pdc.assignment.gui.model.GameModel;
import pdc.assignment.gui.view.GameView;

/**
 *
 * @author Weaselcore
 */
public class PDCAssignment1 {

    /**
     * Entry point to start a game.
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        Boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        String saveFolderString = "src/pdc/assignment/saves/";
        File saveFolder = new File(saveFolderString);

        GameView menuView = new GameView();
        GameModel menuModel = new GameModel();
        GameController menuController = new GameController(menuModel, menuView);
        menuView.addController(menuController);
        
        while (isRunning) {

            System.out.println("\nKINGDOM FIGHTERS\n");
            System.out.println("[#1: New Game]");
            System.out.println("[#2: Load Game]");
            System.out.println("[#3: Rules]");
            System.out.println("[#4: Delete Save File]");
            System.out.println("[#5: Exit]");
            System.out.print("Input: ");

            String result = input.nextLine();

            if ("1".equals(result)) {
                GameSession gameSession = new GameSession();
                break;
            } else if ("2".equals(result)) {

                File fileResult = getSaveFile(saveFolder);
                if (null != fileResult) {
                    GameSession gameSession = new GameSession(fileResult);
                } else {
                    System.out.println("Please input a valid file number.");
                }
            } else if ("3".equals(result)) {
                System.out.println(getRules());
            } else if ("4".equals(result)) {
                try {
                    File fileResult = getSaveFile(saveFolder);
                    if (null != fileResult) {
                        if (fileResult.delete()) {
                            System.out.println("File has been deleted.");
                        } else {
                            System.out.println("File failed to be deleted.");
                        }
                    } else {
                        System.out.println("Please use a valid input.");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else if ("5".equals(result)) {
                System.out.println("Thanks for playing!\n");
                System.exit(0);
            } else {
                System.out.println("Please input a proper option!");
            }
        }
    }

    // Used to retrieve list of saves for loading and deletion of files.
    private static File getSaveFile(File saveFolder) throws Exception {

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
                if (file.isFile()) {
                    System.out.println("[#" + (count + 1) + ": " + file.getName() + "]");
                    saveFileList.add(file);
                    count += 1;
                }
            }

            int saveChoice = scanner.nextInt();

            if (saveChoice <= count) {
                return (File) saveFileList.get(saveChoice - 1);
            } else {
                return null;
            }
        }
        return null;
    }

    // Last minute function to return some rules to improve user experience.
    private static String getRules() {

        String rules = "\n* This is a level based game that has you fight monsters."
                + "\n* There is a level bar that shows up every new level."
                + "\n* Each monster is randomly generated with different stats."
                + "\n* You must defeat them in order to move on."
                + "\n* When defeated, monsters will drop loot that will boost stats."
                + "\n* Potions will go into your inventory' armour/weapons will auto-equip if better."
                + "\n* Each turn will charge up a super attack bar."
                + "\n* When maxed out, your next attack will deal double damage."
                + "\n* You cannot use a potion to go over max health/super attack."
                + "\n* You can use as many potions before attacking."
                + "\n* Enjoy!";

        return rules;

    }
}
